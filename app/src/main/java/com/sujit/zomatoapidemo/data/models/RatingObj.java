package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingObj implements Parcelable {

    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("bg_color")
    @Expose
    private BgColor bgColor;

    protected RatingObj(Parcel in) {
        bgColor = in.readParcelable(BgColor.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bgColor, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RatingObj> CREATOR = new Creator<RatingObj>() {
        @Override
        public RatingObj createFromParcel(Parcel in) {
            return new RatingObj(in);
        }

        @Override
        public RatingObj[] newArray(int size) {
            return new RatingObj[size];
        }
    };


    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public BgColor getBgColor() {
        return bgColor;
    }

    public void setBgColor(BgColor bgColor) {
        this.bgColor = bgColor;
    }

    public static Creator<RatingObj> getCREATOR() {
        return CREATOR;
    }
}