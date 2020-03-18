package com.sujit.zomatoapidemo.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sujit.zomatoapidemo.R;
import com.sujit.zomatoapidemo.data.models.Restaurant;
import com.sujit.zomatoapidemo.databinding.HomeActivityBinding;
import com.sujit.zomatoapidemo.utils.RecyclerViewScrollListener;

import java.util.List;

import javax.inject.Inject;

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

        binding.rvRestaurant.addOnScrollListener(new RecyclerViewScrollListener(binding.rvRestaurant) {
            @Override
            public boolean isLastPage() {
//                Log.e(TAG, "isLastPage: calling");
                if (viewModel.isLastPage()) {
                    restaurantAdapter.hideFooter();
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void loadMore() {
                viewModel.fetchRestraurantByName();
            }
        });


        /* This is to handle configuration changes:
         * during configuration change, when the activity
         * is recreated, we check if the viewModel
         * contains the list data. If so, there is no
         * need to call the api or load data from cache again */

        if (viewModel.getRestaurants().isEmpty()) {
            Log.e(TAG, "initialiseView: first time");
            hideList();
            showProgress();
            viewModel.fetchRestraurantByName();
        } else {
            Log.e(TAG, "initialiseView: display old data");
            hideProgress();
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
//        Intent intent = new Intent(getActivity(), GitRepoDetailsActivity.class);
//        intent.putExtra(GITREPOENTITY_INTENT, gitRepoEntity);
//        getActivity().startActivity(intent);
    }
}
