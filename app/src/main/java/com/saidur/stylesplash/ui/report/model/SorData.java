package com.saidur.stylesplash.ui.report.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SorData {
    @SerializedName("oid")
    @Expose
    private String oid;
    @SerializedName("compid")
    @Expose
    private String compid;
    @SerializedName("oCode")
    @Expose
    private String oCode;
    @SerializedName("oDate")
    @Expose
    private String oDate;
    @SerializedName("custid")
    @Expose
    private String custid;
    @SerializedName("tAmount")
    @Expose
    private String tAmount;
    @SerializedName("paidAmount")
    @Expose
    private String paidAmount;
    @SerializedName("dueAmount")
    @Expose
    private String dueAmount;
    @SerializedName("scost")
    @Expose
    private String scost;
    @SerializedName("dOption")
    @Expose
    private String dOption;
    @SerializedName("shmethod")
    @Expose
    private String shmethod;
    @SerializedName("note")
    @Expose
    private String note;
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
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;

    public SorData() {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getoCode() {
        return oCode;
    }

    public void setoCode(String oCode) {
        this.oCode = oCode;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String gettAmount() {
        return tAmount;
    }

    public void settAmount(String tAmount) {
        this.tAmount = tAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getScost() {
        return scost;
    }

    public void setScost(String scost) {
        this.scost = scost;
    }

    public String getdOption() {
        return dOption;
    }

    public void setdOption(String dOption) {
        this.dOption = dOption;
    }

    public String getShmethod() {
        return shmethod;
    }

    public void setShmethod(String shmethod) {
        this.shmethod = shmethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
