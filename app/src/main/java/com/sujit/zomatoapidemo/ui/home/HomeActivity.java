package com.sujit.zomatoapidemo.ui.home;

import android.os.Bundle;
import android.util.Log;

import com.sujit.zomatoapidemo.R;
import com.sujit.zomatoapidemo.databinding.HomeActivityBinding;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity {


    private String TAG = getClass().getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    HomeActivityViewModel viewModel;
    HomeActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        initialiseViewModel();
        initialiseView();

    }

    private void initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnFetchApi.setOnClickListener(view -> viewModel.fetchRestraurantByName());
    }

    private void initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeActivityViewModel.class);
    }
}
