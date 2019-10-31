package com.oldbie.apflux.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("news_type")
    @Expose
    private String mNewType;

    @SerializedName("content")
    @Expose
    private String mContent;
    @SerializedName("date")
    @Expose
    private String mDate;
    @SerializedName("post_meta")
    @Expose
    private String mPostMeta;

    public News() {
    }

    public News(String mId, String mTitle, String mNewType, String mContent, String mDate, String mPostMeta) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mNewType = mNewType;
        this.mContent = mContent;
        this.mDate = mDate;
        this.mPostMeta = mPostMeta;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmNewType() {
        return mNewType;
    }

    public void setmNewType(String mNewType) {
        this.mNewType = mNewType;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmPostMeta() {
        return mPostMeta;
    }

    public void setmPostMeta(String mPostMeta) {
        this.mPostMeta = mPostMeta;
    }
}
