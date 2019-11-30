package com.oldbie.apflux.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarkDetail {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mark_type")
    @Expose
    private String markType;
    @SerializedName("percentage")
    @Expose
    private Integer percentage;
    @SerializedName("mark")
    @Expose
    private Integer mark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarkType() {
        return markType;
    }

    public void setMarkType(String markType) {
        this.markType = markType;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
