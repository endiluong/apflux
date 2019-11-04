package com.oldbie.apflux.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseTimeTable implements Serializable, Parcelable {

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
    private ArrayList<TimeTable> data = new ArrayList<>();

    public ResponseTimeTable(Integer error, Integer result, String message, ArrayList<TimeTable> data) {
        this.error = error;
        this.result = result;
        this.message = message;
        this.data = data;
    }

    protected ResponseTimeTable(Parcel in) {
    }


    public static final Creator<ResponseTimeTable> CREATOR = new Creator<ResponseTimeTable>() {
        @Override
        public ResponseTimeTable createFromParcel(Parcel in) {
            return new ResponseTimeTable( in );
        }

        @Override
        public ResponseTimeTable[] newArray(int size) {
            return new ResponseTimeTable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(result);
        dest.writeValue(message);
        dest.writeList(data);
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

    public ArrayList<TimeTable> getData() {
        return data;
    }

    public void setData(ArrayList<TimeTable> data) {
        this.data = data;
    }
}
