package com.oldbie.apflux.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Serializable, Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("identification")
    @Expose
    private String identification;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("major")
    @Expose
    private String major;
    @SerializedName("specialize")
    @Expose
    private String specialize;

    @SerializedName("access_token")
    @Expose
    private String token;

    @SerializedName("class")
    @Expose
    private String course;

    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("update_date")
    @Expose
    private String updateDate;
    @SerializedName("delete_status")
    @Expose
    private String deleteStatus;
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    };
    private final static long serialVersionUID = 6923745104029015873L;

    protected User(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
        this.identification = ((String) in.readValue((String.class.getClassLoader())));
        this.studentId = ((String) in.readValue((String.class.getClassLoader())));
        this.birthday = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.major = ((String) in.readValue((String.class.getClassLoader())));
        this.specialize = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.course = ((String) in.readValue((String.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
        this.updateDate = ((String) in.readValue((String.class.getClassLoader())));
        this.deleteStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param specialize
     * @param startDate
     * @param birthday
     * @param phone
     * @param status
     * @param avatar
     * @param deleteStatus
     * @param password
     * @param updateDate
     * @param course
     * @param id
     * @param username
     * @param email
     * @param address
     * @param studentId
     * @param identification
     * @param name
     * @param token
     * @param gender
     * @param createDate
     * @param major
     */
    public User(String id, String username, String password, String email, String name, String phone, String address, String avatar, String identification, String studentId, String birthday, String gender, String major, String specialize, String course, String token, String startDate, String status, String createDate, String updateDate, String deleteStatus) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.identification = identification;
        this.studentId = studentId;
        this.birthday = birthday;
        this.gender = gender;
        this.major = major;
        this.specialize = specialize;
        this.token = token;
        this.course = course;
        this.startDate = startDate;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteStatus = deleteStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(username);
        dest.writeValue(password);
        dest.writeValue(email);
        dest.writeValue(name);
        dest.writeValue(phone);
        dest.writeValue(address);
        dest.writeValue(avatar);
        dest.writeValue(identification);
        dest.writeValue(studentId);
        dest.writeValue(birthday);
        dest.writeValue(gender);
        dest.writeValue(major);
        dest.writeValue(specialize);
        dest.writeValue(course);
        dest.writeValue(token);
        dest.writeValue(startDate);
        dest.writeValue(status);
        dest.writeValue(createDate);
        dest.writeValue(updateDate);
        dest.writeValue(deleteStatus);
    }

    public int describeContents() {
        return 0;
    }
}
