package com.sujit.zomatoapidemo.data.source;


import com.sujit.zomatoapidemo.data.models.ZomatoResponse;
import com.sujit.zomatoapidemo.data.remote.APIService;

import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import com.sujit.zomatoapidemo.data.Resource;

@Singleton
public class DataSource {

    private APIService apiService;
    private String TAG = getClass().getSimpleName();


    public DataSource(APIService apiService) {
        this.apiService = apiService;

    }

    public Observable<Resource<ZomatoResponse>> getRestraurantsByName(String queryParams, int currentPage) {
        return apiService.getRestaurantsByName(queryParams, currentPage)
                .flatMap(response -> Observable.just(response.isSuccessful()
                        ? Resource.success(response.body())
                        : Resource.error("", new ZomatoResponse())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Resource<ZomatoResponse>> getRestraurants(double lat, double lon, int currentPage) {
        return apiService.getRestaurantsByLocation(lat, lon, currentPage)
                .flatMap(response -> Observable.just(response.isSuccessful()
                        ? Resource.success(response.body())
                        : Resource.error("", new ZomatoResponse())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
