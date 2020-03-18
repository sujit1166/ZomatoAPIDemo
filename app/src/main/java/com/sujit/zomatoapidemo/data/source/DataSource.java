package com.sujit.zomatoapidemo.data.source;


import com.sujit.zomatoapidemo.data.remote.APIService;

import javax.inject.Singleton;

@Singleton
public class DataSource {

    private APIService apiService;
    private String TAG = getClass().getSimpleName();


    public DataSource(APIService apiService) {
        this.apiService = apiService;

    }

}
