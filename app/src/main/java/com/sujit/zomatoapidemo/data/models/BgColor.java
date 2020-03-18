package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BgColor implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tint")
    @Expose
    private String tint;

    protected BgColor(Parcel in) {
        type = in.readString();
        tint = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(tint);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BgColor> CREATOR = new Creator<BgColor>() {
        @Override
        public BgColor createFromParcel(Parcel in) {
            return new BgColor(in);
        }

        @Override
        public BgColor[] newArray(int size) {
            return new BgColor[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTint() {
        return tint;
    }

    public void setTint(String tint) {
        this.tint = tint;
    }

    public static Creator<BgColor> getCREATOR() {
        return CREATOR;
    }
}