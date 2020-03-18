package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant implements Parcelable {

    @SerializedName("restaurant")
    @Expose
    private RestaurantItem restaurant;

    protected Restaurant(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };


    public RestaurantItem getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantItem restaurant) {
        this.restaurant = restaurant;
    }

    public static Creator<Restaurant> getCREATOR() {
        return CREATOR;
    }



}