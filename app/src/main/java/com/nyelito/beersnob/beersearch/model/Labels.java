package com.nyelito.beersnob.beersearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Labels implements Parcelable {

    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("large")
    @Expose
    private String large;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }


    protected Labels(Parcel in) {
        icon = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(icon);
        dest.writeString(medium);
        dest.writeString(large);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Labels> CREATOR = new Parcelable.Creator<Labels>() {
        @Override
        public Labels createFromParcel(Parcel in) {
            return new Labels(in);
        }

        @Override
        public Labels[] newArray(int size) {
            return new Labels[size];
        }
    };
}