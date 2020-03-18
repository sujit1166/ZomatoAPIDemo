package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZomatoResponse implements Parcelable {

    @SerializedName("results_found")
    @Expose
    private int resultsFound;
    @SerializedName("results_start")
    @Expose
    private int resultsStart;
    @SerializedName("results_shown")
    @Expose
    private int resultsShown;
    @SerializedName("restaurants")
    @Expose
    private List<Restaurant> restaurants;

    protected ZomatoResponse(Parcel in) {
        resultsFound = in.readInt();
        resultsStart = in.readInt();
        resultsShown = in.readInt();
    }

    public ZomatoResponse() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resultsFound);
        dest.writeInt(resultsStart);
        dest.writeInt(resultsShown);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ZomatoResponse> CREATOR = new Creator<ZomatoResponse>() {
        @Override
        public ZomatoResponse createFromParcel(Parcel in) {
            return new ZomatoResponse(in);
        }

        @Override
        public ZomatoResponse[] newArray(int size) {
            return new ZomatoResponse[size];
        }
    };


    public int getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(int resultsFound) {
        this.resultsFound = resultsFound;
    }

    public int getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(int resultsStart) {
        this.resultsStart = resultsStart;
    }

    public int getResultsShown() {
        return resultsShown;
    }

    public void setResultsShown(int resultsShown) {
        this.resultsShown = resultsShown;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public static Creator<ZomatoResponse> getCREATOR() {
        return CREATOR;
    }
}