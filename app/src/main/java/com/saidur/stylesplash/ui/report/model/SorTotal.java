package com.saidur.stylesplash.ui.report.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SorTotal {
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

    public SorTotal() {
    }

    public SorTotal(Integer torder, Integer porder, Integer corder, Integer sorder) {
        this.torder = torder;
        this.porder = porder;
        this.corder = corder;
        this.sorder = sorder;
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
