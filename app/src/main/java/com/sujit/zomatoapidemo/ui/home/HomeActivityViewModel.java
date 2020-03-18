package com.sujit.zomatoapidemo.ui.home;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.sujit.zomatoapidemo.data.models.Location;
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

    private Location location;
    private int currentPage;
    private int totalResults;

    @Inject
    public HomeActivityViewModel(APIService apiService) {
        dataSource = new DataSource(apiService);
        resListLiveData = new SingleLiveEvent<>();
        isError = new SingleLiveEvent<>();
        restaurants = new ArrayList<>();
        currentPage = 0;
        totalResults = 0;
    }


    @SuppressLint("CheckResult")
    public void fetchRestraurant() {
        if (location == null || TextUtils.isEmpty(location.getAddress())) return;

        dataSource.getRestraurants(location, currentPage)
                .subscribe(resource -> {
                    if (resource.isSuccess()) {
                        restaurants.addAll(resource.data.getRestaurants());
                        resListLiveData.setValue(resource.data.getRestaurants());
                        currentPage = currentPage + 20;
                        totalResults = resource.data.getResultsFound();
                    } else {
                        if (restaurants.isEmpty()) resListLiveData.setValue(null); // first page
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        restaurants.clear();
        currentPage = 0;
        totalResults = 0;
    }


    public SingleLiveEvent<Boolean> isError() {
        return isError;
    }

    public boolean isLastPage() {
        return restaurants.isEmpty() || totalResults == 0 || totalResults < 20 || totalResults <= currentPage;
    }
}
