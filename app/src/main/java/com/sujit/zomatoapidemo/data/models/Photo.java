package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {

    @SerializedName("photo")
    @Expose
    private PhotoItem photo;

    protected Photo(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };


    public PhotoItem getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoItem photo) {
        this.photo = photo;
    }

    public static Creator<Photo> getCREATOR() {
        return CREATOR;
    }
}