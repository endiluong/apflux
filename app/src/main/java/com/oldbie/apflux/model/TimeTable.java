package com.oldbie.apflux.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeTable {

    @SerializedName( "id" )
    @Expose
    private String mId;
    @SerializedName( "room" )
    @Expose
    private String mRoom;
    @SerializedName( "place" )
    @Expose
    private String mPlace;
    @SerializedName( "weekday" )
    @Expose
    private int mWeekDay;
    @SerializedName( "tutor" )
    @Expose
    private String mTutor;
    @SerializedName( "date" )
    @Expose
    private String mDate;
    @SerializedName( "subject" )
    @Expose
    private String mSubject;
    @SerializedName( "subject_id" )
    @Expose
    private String mIdSubject;
    @SerializedName( "class" )
    @Expose
    private String mClass;
    @SerializedName( "slot" )
    @Expose
    private String mSlot;

    public TimeTable() {
    }

    public TimeTable(String mId, String mRoom, String mPlace, int mWeekDay, String mTutor, String mDate, String mSubject, String mIdSubject, String mClass, String mSlot) {
        this.mId = mId;
        this.mRoom = mRoom;
        this.mPlace = mPlace;
        this.mWeekDay = mWeekDay;
        this.mTutor = mTutor;
        this.mDate = mDate;
        this.mSubject = mSubject;
        this.mIdSubject = mIdSubject;
        this.mClass = mClass;
        this.mSlot = mSlot;
    }

    public int getmWeekDay() {
        return mWeekDay;
    }

    public void setmWeekDay(int mWeekDay) {
        this.mWeekDay = mWeekDay;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmRoom() {
        return mRoom;
    }

    public void setmRoom(String mRoom) {
        this.mRoom = mRoom;
    }

    public String getmPlace() {
        return mPlace;
    }

    public void setmPlace(String mPlace) {
        this.mPlace = mPlace;
    }

    public String getmTutor() {
        return mTutor;
    }

    public void setmTutor(String mTutor) {
        this.mTutor = mTutor;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmIdSubject() {
        return mIdSubject;
    }

    public void setmIdSubject(String mIdSubject) {
        this.mIdSubject = mIdSubject;
    }

    public String getmClass() {
        return mClass;
    }

    public void setmClass(String mClass) {
        this.mClass = mClass;
    }

    public String getmSlot() {
        return mSlot;
    }

    public void setmSlot(String mSlot) {
        this.mSlot = mSlot;
    }
}
