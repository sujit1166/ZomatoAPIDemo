package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HasMenuStatus implements Parcelable {

    @SerializedName("delivery")
    @Expose
    private int delivery;
    @SerializedName("takeaway")
    @Expose
    private int takeaway;

    protected HasMenuStatus(Parcel in) {
        delivery = in.readInt();
        takeaway = in.readInt();
    }

    public static final Creator<HasMenuStatus> CREATOR = new Creator<HasMenuStatus>() {
        @Override
        public HasMenuStatus createFromParcel(Parcel in) {
            return new HasMenuStatus(in);
        }

        @Override
        public HasMenuStatus[] newArray(int size) {
            return new HasMenuStatus[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(delivery);
        parcel.writeInt(takeaway);
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getTakeaway() {
        return takeaway;
    }

    public void setTakeaway(int takeaway) {
        this.takeaway = takeaway;
    }

    public static Creator<HasMenuStatus> getCREATOR() {
        return CREATOR;
    }
}