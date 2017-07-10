package com.nyelito.beersnob.beersearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeerSearchData implements Parcelable {

    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("numberOfPages")
    @Expose
    private Integer numberOfPages;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("data")
    @Expose
    private List<Beer> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Beer> getData() {
        return data;
    }

    public void setData(List<Beer> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    protected BeerSearchData(Parcel in) {
        currentPage = in.readByte() == 0x00 ? null : in.readInt();
        numberOfPages = in.readByte() == 0x00 ? null : in.readInt();
        totalResults = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            data = new ArrayList<Beer>();
            in.readList(data, Beer.class.getClassLoader());
        } else {
            data = null;
        }
        status = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (currentPage == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(currentPage);
        }
        if (numberOfPages == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(numberOfPages);
        }
        if (totalResults == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(totalResults);
        }
        if (data == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(data);
        }
        dest.writeString(status);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BeerSearchData> CREATOR = new Parcelable.Creator<BeerSearchData>() {
        @Override
        public BeerSearchData createFromParcel(Parcel in) {
            return new BeerSearchData(in);
        }

        @Override
        public BeerSearchData[] newArray(int size) {
            return new BeerSearchData[size];
        }
    };
}