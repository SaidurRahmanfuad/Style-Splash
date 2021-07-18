package com.saidur.stylesplash.ui.customer.network;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saidur.stylesplash.utils.Const;

public class CustomerListData {
    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("compid")
    @Expose
    private String compid;
    @SerializedName("cus_id")
    @Expose
    private String cusId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("status")
    @Expose
    private String status;
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

    public String from;
    public CustomerListData() {
    }

    public CustomerListData(String customerID, String compid, String cusId, String customerName, String mobile, String email, String address, String balance, String status, String regby, String regdate, String upby, String update) {
        this.customerID = customerID;
        this.compid = compid;
        this.cusId = cusId;
        this.customerName = customerName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.balance = balance;
        this.status = status;
        this.regby = regby;
        this.regdate = regdate;
        this.upby = upby;
        this.update = update;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public CustomerListData(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        if(from.equals("fromaddcustomer"))
        {
            return  mobile;
        }
        else {
            return mobile +"("+customerName+")";
        }

    }
}
