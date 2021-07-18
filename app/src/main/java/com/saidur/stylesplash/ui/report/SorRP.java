package com.saidur.stylesplash.ui.report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saidur.stylesplash.ui.report.model.SorData;

import java.util.List;

public class SorRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<SorData> data = null;
    @SerializedName("torder")
    @Expose
    private Integer torder;
    @SerializedName("porder")
    @Expose
    private Integer porder;
    @SerializedName("corder")
    @Expose
    private Integer corder;
    @SerializedName("sorder")
    @Expose
    private Integer sorder;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SorData> getData() {
        return data;
    }

    public void setData(List<SorData> data) {
        this.data = data;
    }

    public Integer getTorder() {
        return torder;
    }

    public void setTorder(Integer torder) {
        this.torder = torder;
    }

    public Integer getPorder() {
        return porder;
    }

    public void setPorder(Integer porder) {
        this.porder = porder;
    }

    public Integer getCorder() {
        return corder;
    }

    public void setCorder(Integer corder) {
        this.corder = corder;
    }

    public Integer getSorder() {
        return sorder;
    }

    public void setSorder(Integer sorder) {
        this.sorder = sorder;
    }
}
