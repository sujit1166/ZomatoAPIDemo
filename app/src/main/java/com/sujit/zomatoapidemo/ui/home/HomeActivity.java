package com.sujit.zomatoapidemo.ui.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sujit.zomatoapidemo.R;
import com.sujit.zomatoapidemo.data.models.Restaurant;
import com.sujit.zomatoapidemo.databinding.HomeActivityBinding;
import com.sujit.zomatoapidemo.ui.location.LocationActivity;
import com.sujit.zomatoapidemo.utils.AppConstants;
import com.sujit.zomatoapidemo.utils.NetworkUtils;
import com.sujit.zomatoapidemo.utils.RecyclerViewScrollListener;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity {


    private String TAG = getClass().getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private HomeActivityViewModel viewModel;
    private HomeActivityBinding binding;
    private RestaurantAdapter restaurantAdapter;

    private final int GET_LOCATION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        initialiseViewModel();
        initialiseView();
    }

    private void initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.rvRestaurant.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        restaurantAdapter = new RestaurantAdapter(getApplicationContext());
        binding.rvRestaurant.setAdapter(restaurantAdapter);
        restaurantAdapter.setOnItemClickListener(this::navigateToRestaurantDetailsActivity);
        binding.rlHeader.setOnClickListener(v -> navigateToLocationActivity());

        binding.rvRestaurant.addOnScrollListener(new RecyclerViewScrollListener(binding.rvRestaurant) {
            @Override
            public boolean isLastPage() {
                if (viewModel.isLastPage()) {
                    restaurantAdapter.hideFooter();
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void loadMore() {
                if (NetworkUtils.isNetworkOnline(HomeActivity.this)) {
                    viewModel.fetchRestraurant();
                } else {
                    Toast.makeText(HomeActivity.this, R.string.please_turn_on_internet, Toast.LENGTH_SHORT).show();
                }
            }
        });


        /* This is to handle configuration changes:
         * during configuration change, when the activity
         * is recreated, we check if the viewModel
         * contains the list data. If so, there is no
         * need to call the api or load data from cache again */

        hideProgress();
        if (viewModel.getLocation() == null) {
            showLocationNeedDialog();
        } else if (viewModel.getRestaurants().isEmpty()) {
            Log.e(TAG, "initialiseView: first time");
            fetchRestraurantsData();
        } else {
            Log.e(TAG, "initialiseView: display old data");
            binding.tvCurrentLocation.setText(viewModel.getLocation().getAddress());
            showList(viewModel.getRestaurants());
        }
    }

    private void initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeActivityViewModel.class);
        viewModel.getResListLiveData().observe(this, restaurantList -> {
            if (!viewModel.getRestaurants().isEmpty()) {
                hideProgress();
                showList(viewModel.getRestaurants());
            } else {
                showEmptyView();
            }
        });
        viewModel.isError().observe(this, aBoolean -> Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show());

    }

    private void fetchRestraurantsData() {
        if (NetworkUtils.isNetworkOnline(HomeActivity.this)) {
            hideList();
            showProgress();
            restaurantAdapter.refreshItems();
            viewModel.fetchRestraurant();
        } else {
            showEmptyView();
            Toast.makeText(HomeActivity.this, R.string.please_turn_on_internet, Toast.LENGTH_SHORT).show();
        }
    }



    private void showProgress() {
        binding.viewLoader.rootView.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        binding.viewLoader.rootView.setVisibility(View.GONE);
    }

    private void showList(List<Restaurant> restaurantList) {
        restaurantAdapter.setItems(restaurantList);
        restaurantAdapter.notifyDataSetChanged();
        binding.viewEmpty.emptyContainer.setVisibility(View.GONE);
        binding.rvRestaurant.setVisibility(View.VISIBLE);
    }

    private void hideList() {
        binding.rvRestaurant.setVisibility(View.GONE);
    }

    private void showEmptyView() {
        hideProgress();
        binding.viewEmpty.emptyContainer.setVisibility(View.VISIBLE);
    }

    public void navigateToRestaurantDetailsActivity(Restaurant restaurant) {

    }


    private void showLocationNeedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.location_required))
                .setMessage(R.string.location_tittle)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    navigateToLocationActivity();
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    finish();
                })
                .setCancelable(false);
        builder.create().show();
    }

    private void navigateToLocationActivity() {
        Intent intent = new Intent(this, LocationActivity.class);
        if (viewModel.getLocation() != null) {
            intent.putExtra(AppConstants.ADDRESS, viewModel.getLocation());
        }
        startActivityForResult(intent, GET_LOCATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_LOCATION) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    viewModel.setLocation(data.getParcelableExtra(AppConstants.ADDRESS));
                    binding.tvCurrentLocation.setText(viewModel.getLocation().getAddress());
                    fetchRestraurantsData();
                }
            } else if (resultCode == RESULT_CANCELED) {
                if (viewModel.getLocation() == null) {
                    showLocationNeedDialog();
                }
            }
        }
    }
}
