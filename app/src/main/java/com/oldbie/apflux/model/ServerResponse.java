package com.oldbie.apflux.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerResponse implements Serializable, Parcelable {

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

    private ArrayList<User> data = new ArrayList<>();

    public final static Parcelable.Creator<ServerResponse> CREATOR = new Parcelable.Creator<ServerResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServerResponse createFromParcel(Parcel in) {
            return new ServerResponse(in);
        }

        public ServerResponse[] newArray(int size) {
            return (new ServerResponse[size]);
        }

    };


    private final static long serialVersionUID = -1532097521191403595L;

    protected ServerResponse(Parcel in) {
        this.error = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.result = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (com.oldbie.apflux.model.User.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ServerResponse() {
    }

    /**
     *
     * @param message
     * @param result
     * @param error
     * @param data
     */
    public ServerResponse(Integer error, Integer result, String message, ArrayList<User> data) {
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

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(result);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}