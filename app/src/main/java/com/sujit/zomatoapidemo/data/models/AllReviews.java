package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllReviews implements Parcelable {

@SerializedName("reviews")
@Expose
private List<Review> reviews = null;

    protected AllReviews(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AllReviews> CREATOR = new Creator<AllReviews>() {
        @Override
        public AllReviews createFromParcel(Parcel in) {
            return new AllReviews(in);
        }

        @Override
        public AllReviews[] newArray(int size) {
            return new AllReviews[size];
        }
    };


    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public static Creator<AllReviews> getCREATOR() {
        return CREATOR;
    }
}