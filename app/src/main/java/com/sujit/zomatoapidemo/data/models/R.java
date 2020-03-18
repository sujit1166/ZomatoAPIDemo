package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class R implements Parcelable {

    @SerializedName("has_menu_status")
    @Expose
    private HasMenuStatus hasMenuStatus;
    @SerializedName("res_id")
    @Expose
    private int resId;

    protected R(Parcel in) {
        hasMenuStatus = in.readParcelable(HasMenuStatus.class.getClassLoader());
        resId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(hasMenuStatus, flags);
        dest.writeInt(resId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<R> CREATOR = new Creator<R>() {
        @Override
        public R createFromParcel(Parcel in) {
            return new R(in);
        }

        @Override
        public R[] newArray(int size) {
            return new R[size];
        }
    };

    public HasMenuStatus getHasMenuStatus() {
        return hasMenuStatus;
    }

    public void setHasMenuStatus(HasMenuStatus hasMenuStatus) {
        this.hasMenuStatus = hasMenuStatus;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public static Creator<R> getCREATOR() {
        return CREATOR;
    }
}