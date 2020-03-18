package com.sujit.zomatoapidemo.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;

import com.sujit.zomatoapidemo.data.models.Restaurant;
import com.sujit.zomatoapidemo.data.remote.APIService;
import com.sujit.zomatoapidemo.data.source.DataSource;
import com.sujit.zomatoapidemo.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class HomeActivityViewModel extends ViewModel {


    private final String TAG = getClass().getSimpleName();

    private DataSource dataSource;
    private SingleLiveEvent<List<Restaurant>> resListLiveData;
    private List<Restaurant> restaurants;
    private SingleLiveEvent<Boolean> isError;
    private int currentPage;
    private int totalResults;

    private SingleLiveEvent<String> queryParams;

    @Inject
    public HomeActivityViewModel(APIService apiService) {
        dataSource = new DataSource(apiService);
        resListLiveData = new SingleLiveEvent<>();
        queryParams = new SingleLiveEvent<>();
        isError = new SingleLiveEvent<>();
        restaurants = new ArrayList<>();
        currentPage = 0;
        totalResults = 0;

        // TODO
        queryParams.setValue("Dadar");
    }

    @SuppressLint("CheckResult")
    public void fetchRestraurantByName() {
        Log.e(TAG, "fetchRestraurantByName: ");
        dataSource.getRestraurantsByName(queryParams.getValue(), currentPage)
                .subscribe(resource -> {
                    if (resource.isSuccess()) {
                        restaurants.addAll(resource.data.getRestaurants());
                        resListLiveData.setValue(resource.data.getRestaurants());
                        currentPage = currentPage + 20;
                        totalResults = resource.data.getResultsFound();
                    } else {
                        isError.setValue(true);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void fetchRestraurantsByLocation(double lat, double lon) {
        if (currentPage != 0) currentPage = currentPage + 20;
        dataSource.getRestraurants(lat, lon, currentPage)
                .subscribe(resource -> {
                    if (resource.isSuccess()) {
                        restaurants.addAll(resource.data.getRestaurants());
                        resListLiveData.setValue(resource.data.getRestaurants());
                        totalResults = resource.data.getResultsFound();
                    } else {
                        isError.setValue(true);
                    }
                });
    }

    public LiveData<List<Restaurant>> getResListLiveData() {
        return resListLiveData;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }


    public SingleLiveEvent<String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams.setValue(queryParams);
    }

    public SingleLiveEvent<Boolean> isError() {
        return isError;
    }

    public boolean isLastPage() {
        Log.e(TAG, "isLastPage: currentPage "+currentPage);
        Log.i(TAG, "isLastPage: totalResults "+totalResults);
        return restaurants.isEmpty() || totalResults == 0 || totalResults < 20 || totalResults <= currentPage;
    }
}
