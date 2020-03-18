package com.sujit.zomatoapidemo.di.module;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sujit.zomatoapidemo.BuildConfig;
import com.sujit.zomatoapidemo.data.remote.APIService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);
        httpClient.addInterceptor(provideHeaderInterceptor());
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        return httpClient.build();
    }


    private static Interceptor provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("user-key", BuildConfig.TOKEN)
                        .method(original.method(), original.body()).build();

                return chain.proceed(request);
            }
        };
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://developers.zomato.com/api/v2.1/")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    APIService provideGithubApiService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }
}
