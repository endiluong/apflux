package com.oldbie.apflux.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseMark {
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
    private ArrayList<Mark> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseMark() {
    }

    /**
     *
     * @param result
     * @param data
     * @param error
     * @param message
     */
    public ResponseMark(Integer error, Integer result, String message, ArrayList<Mark> data) {
        super();
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

    public ArrayList<Mark> getData() {
        return data;
    }

    public void setData(ArrayList<Mark> data) {
        this.data = data;
    }

}
