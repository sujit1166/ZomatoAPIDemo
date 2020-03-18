package com.sujit.zomatoapidemo.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;

import com.sujit.zomatoapidemo.data.models.Restaurant;
import com.sujit.zomatoapidemo.data.remote.APIService;
import com.sujit.zomatoapidemo.data.source.DataSource;
import com.sujit.zomatoapidemo.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class HomeActivityViewModel extends ViewModel {


    private final String TAG = getClass().getSimpleName();

    private DataSource dataSource;
    private SingleLiveEvent<List<Restaurant>> resListLiveData;

    @Inject
    public HomeActivityViewModel(APIService apiService) {
        dataSource = new DataSource(apiService);
        resListLiveData = new SingleLiveEvent<>();
    }

    @SuppressLint("CheckResult")
    public void fetchRestraurantByName() {
        Log.e(TAG, "fetchRestraurantByName: ");
        dataSource.getRestraurantsByName("DADAR", 0)
                .subscribe(resource -> {
                    if (resource.isSuccess()) {
                        Log.e(TAG, "fetchRestraurantByName: ");
                    } else {
                        Log.e(TAG, "fetchRestraurantByName: ");
                    }
                });
    }
}
