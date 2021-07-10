package com.saidur.stylesplash.ui.login.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("compid")
    @Expose
    private String compid;
    @SerializedName("empid")
    @Expose
    private String empid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("compname")
    @Expose
    private String compname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("userrole")
    @Expose
    private String userrole;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("regby")
    @Expose
    private String regby;
    @SerializedName("regdate")
    @Expose
    private String regdate;
    @SerializedName("upby")
    @Expose
    private String upby;
    @SerializedName("update")
    @Expose
    private String update;

    public LoginData() {
    }

    public LoginData(String uid, String compid, String empid, String name, String compname, String email, String mobile, String password, String userrole, String photo, String status, String otp, String regby, String regdate, String upby, String update) {
        this.uid = uid;
        this.compid = compid;
        this.empid = empid;
        this.name = name;
        this.compname = compname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.userrole = userrole;
        this.photo = photo;
        this.status = status;
        this.otp = otp;
        this.regby = regby;
        this.regdate = regdate;
        this.upby = upby;
        this.update = update;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getRegby() {
        return regby;
    }

    public void setRegby(String regby) {
        this.regby = regby;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getUpby() {
        return upby;
    }

    public void setUpby(String upby) {
        this.upby = upby;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
