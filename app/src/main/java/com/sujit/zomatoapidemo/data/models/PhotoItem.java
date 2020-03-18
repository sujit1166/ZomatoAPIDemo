package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoItem implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("res_id")
    @Expose
    private Integer resId;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("friendly_time")
    @Expose
    private String friendlyTime;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    protected PhotoItem(Parcel in) {
        id = in.readString();
        url = in.readString();
        thumbUrl = in.readString();
        if (in.readByte() == 0) {
            resId = null;
        } else {
            resId = in.readInt();
        }
        caption = in.readString();
        if (in.readByte() == 0) {
            timestamp = null;
        } else {
            timestamp = in.readInt();
        }
        friendlyTime = in.readString();
        if (in.readByte() == 0) {
            width = null;
        } else {
            width = in.readInt();
        }
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(url);
        dest.writeString(thumbUrl);
        if (resId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(resId);
        }
        dest.writeString(caption);
        if (timestamp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(timestamp);
        }
        dest.writeString(friendlyTime);
        if (width == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(width);
        }
        if (height == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(height);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhotoItem> CREATOR = new Creator<PhotoItem>() {
        @Override
        public PhotoItem createFromParcel(Parcel in) {
            return new PhotoItem(in);
        }

        @Override
        public PhotoItem[] newArray(int size) {
            return new PhotoItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getFriendlyTime() {
        return friendlyTime;
    }

    public void setFriendlyTime(String friendlyTime) {
        this.friendlyTime = friendlyTime;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public static Creator<PhotoItem> getCREATOR() {
        return CREATOR;
    }
}