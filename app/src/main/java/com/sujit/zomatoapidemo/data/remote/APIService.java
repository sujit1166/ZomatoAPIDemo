package com.sujit.zomatoapidemo.data.remote;


import com.sujit.zomatoapidemo.data.models.ZomatoResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("search")
    Observable<Response<ZomatoResponse>> getRestaurantsByName(@Query("q") String queryParams,
                                                              @Query("start") int currentPage);

}
