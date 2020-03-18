package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Title implements Parcelable {

    @SerializedName("text")
    @Expose
    private String text;

    protected Title(Parcel in) {
        text = in.readString();
    }

    public static final Creator<Title> CREATOR = new Creator<Title>() {
        @Override
        public Title createFromParcel(Parcel in) {
            return new Title(in);
        }

        @Override
        public Title[] newArray(int size) {
            return new Title[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Creator<Title> getCREATOR() {
        return CREATOR;
    }
}