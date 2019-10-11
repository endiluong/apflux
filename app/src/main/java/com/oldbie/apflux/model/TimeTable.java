package com.oldbie.applux.model;

import android.view.View;

import com.google.gson.annotations.SerializedName;

public class TimeTable {

    @SerializedName( "day_of_week" )
    private String dayOfWeek;
    @SerializedName( "clock" )
    private String clock;
    @SerializedName( "title" )
    private String title;
    @SerializedName( "lesson" )
    private String lesson;
    @SerializedName( "teacher" )
    private String teacher;
    @SerializedName( "room" )
    private String room;
    @SerializedName( "id_room" )
    private String id_room;
    @SerializedName( "id_subject" )
    private String id_subject;
    @SerializedName( "subject" )
    private String subject;
    @SerializedName( "subject_detail" )
    private String subject_detail;

    private View.OnClickListener requestBtnClickListener;


    public TimeTable() {
    }

    public TimeTable(String dayOfWeek, String clock, String title, String lesson, String teacher, String room, String id_room, String id_subject, String subject, String subject_detail) {
        this.dayOfWeek = dayOfWeek;
        this.clock = clock;
        this.title = title;
        this.lesson = lesson;
        this.teacher = teacher;
        this.room = room;
        this.id_room = id_room;
        this.id_subject = id_subject;
        this.subject = subject;
        this.subject_detail = subject_detail;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getId_room() {
        return id_room;
    }

    public void setId_room(String id_room) {
        this.id_room = id_room;
    }

    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject_detail() {
        return subject_detail;
    }

    public void setSubject_detail(String subject_detail) {
        this.subject_detail = subject_detail;
    }
}
