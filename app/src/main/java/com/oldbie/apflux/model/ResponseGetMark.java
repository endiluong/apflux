package com.oldbie.apflux.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseGetMark {

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
    private ArrayList<GetMark> data = new ArrayList<>();

    public ResponseGetMark() {
    }

    public ResponseGetMark(Integer error, Integer result, String message, ArrayList<GetMark> data) {
        this.error = error;
        this.result = result;
        this.message = message;
        this.data = data;
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

    public ArrayList<GetMark> getData() {
        return data;
    }

    public void setData(ArrayList<GetMark> data) {
        this.data = data;
    }
}
