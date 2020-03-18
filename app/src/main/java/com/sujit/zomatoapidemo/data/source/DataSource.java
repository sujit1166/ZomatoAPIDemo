package com.sujit.zomatoapidemo.data.source;


import android.text.TextUtils;

import com.sujit.zomatoapidemo.data.Resource;
import com.sujit.zomatoapidemo.data.models.Location;
import com.sujit.zomatoapidemo.data.models.ZomatoResponse;
import com.sujit.zomatoapidemo.data.remote.APIService;

import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class DataSource {

    private APIService apiService;
    private String TAG = getClass().getSimpleName();


    public DataSource(APIService apiService) {
        this.apiService = apiService;

    }

    public Observable<Resource<ZomatoResponse>> getRestraurants(Location location, int currentPage) {

        if (!TextUtils.isEmpty(location.getLongitude()) && !TextUtils.isEmpty(location.getLongitude())) {
            return apiService.getRestaurantsByLocation(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), currentPage)
                    .flatMap(response -> Observable.just(response.isSuccessful()
                            ? Resource.success(response.body())
                            : Resource.error("", new ZomatoResponse())))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return apiService.getRestaurantsByName(location.getAddress(), currentPage)
                    .flatMap(response -> Observable.just(response.isSuccessful()
                            ? Resource.success(response.body())
                            : Resource.error("", new ZomatoResponse())))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
