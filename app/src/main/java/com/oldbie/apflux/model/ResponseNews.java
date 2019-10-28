package com.oldbie.apflux.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseNews implements Serializable, Parcelable {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<News> data = new ArrayList<>();

    public ResponseNews(Integer error, Integer result, String message, ArrayList<News> data) {
        this.error = error;
        this.result = result;
        this.message = message;
        this.data = data;
    }

    protected ResponseNews(Parcel in) {
    }

    public static final Creator<ResponseNews> CREATOR = new Creator<ResponseNews>() {
        @Override
        public ResponseNews createFromParcel(Parcel in) {
            return new ResponseNews( in );
        }

        @Override
        public ResponseNews[] newArray(int size) {
            return new ResponseNews[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<News> getData() {
        return data;
    }

    public void setData(ArrayList<News> data) {
        this.data = data;
    }
}
