package com.oldbie.apflux.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mark{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("average_grade")
    @Expose
    private Integer averageGrade;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("grade_detail")
    @Expose
    private List<MarkDetail> gradeDetail = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Integer averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MarkDetail> getGradeDetail() {
        return gradeDetail;
    }

    public void setGradeDetail(List<MarkDetail> gradeDetail) {
        this.gradeDetail = gradeDetail;
    }
}
