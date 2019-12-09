package com.oldbie.apflux.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetMark {

    @SerializedName( "id" )
    @Expose
    private String mId;

    @SerializedName( "subject_id" )
    @Expose
    private String mSubjectId;

    @SerializedName( "subject_name" )
    @Expose
    private String mSubjectName;

    @SerializedName( "season" )
    @Expose
    private String mSeason;

    @SerializedName( "average_grade" )
    @Expose
    private String mAverageGrade;

    @SerializedName( "status" )
    @Expose
    private String mStatus;

    @SerializedName( "grade_detail" )
    @Expose
    private ArrayList<GetMarkDetail> arrayList = null;

    public GetMark(String mId, String mSubjectId, String mSubjectName, String mSeason, String mAverageGrade, String mStatus, ArrayList<GetMarkDetail> arrayList) {
        this.mId = mId;
        this.mSubjectId = mSubjectId;
        this.mSubjectName = mSubjectName;
        this.mSeason = mSeason;
        this.mAverageGrade = mAverageGrade;
        this.mStatus = mStatus;
        this.arrayList = arrayList;
    }

    public GetMark() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmSubjectId() {
        return mSubjectId;
    }

    public void setmSubjectId(String mSubjectId) {
        this.mSubjectId = mSubjectId;
    }

    public String getmSubjectName() {
        return mSubjectName;
    }

    public void setmSubjectName(String mSubjectName) {
        this.mSubjectName = mSubjectName;
    }

    public String getmSeason() {
        return mSeason;
    }

    public void setmSeason(String mSeason) {
        this.mSeason = mSeason;
    }

    public String getmAverageGrade() {
        return mAverageGrade;
    }

    public void setmAverageGrade(String mAverageGrade) {
        this.mAverageGrade = mAverageGrade;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ArrayList<GetMarkDetail> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<GetMarkDetail> arrayList) {
        this.arrayList = arrayList;
    }


    //.. ..//
    public class GetMarkDetail{

        @SerializedName( "id" )
        @Expose
        private String mId;

        @SerializedName( "mark_type" )
        @Expose
        private String mMarkType;

        @SerializedName( "percentage" )
        @Expose
        private String mPercentage;

        @SerializedName( "mark" )
        @Expose
        private String mMarkDetail;

        public GetMarkDetail() {
        }

        public GetMarkDetail(String mId, String mMarkType, String mPercentage, String mMarkDetail) {
            this.mId = mId;
            this.mMarkType = mMarkType;
            this.mPercentage = mPercentage;
            this.mMarkDetail = mMarkDetail;
        }

        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public String getmMarkType() {
            return mMarkType;
        }

        public void setmMarkType(String mMarkType) {
            this.mMarkType = mMarkType;
        }

        public String getmPercentage() {
            return mPercentage;
        }

        public void setmPercentage(String mPercentage) {
            this.mPercentage = mPercentage;
        }

        public String getmMarkDetail() {
            return mMarkDetail;
        }

        public void setmMarkDetail(String mMarkDetail) {
            this.mMarkDetail = mMarkDetail;
        }
    }

}

