package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review implements Parcelable {

    @SerializedName("review")
    @Expose
    private List<Object> review;

    protected Review(Parcel in) {
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public List<Object> getReview() {
        return review;
    }

    public void setReview(List<Object> review) {
        this.review = review;
    }

    public static Creator<Review> getCREATOR() {
        return CREATOR;
    }
}