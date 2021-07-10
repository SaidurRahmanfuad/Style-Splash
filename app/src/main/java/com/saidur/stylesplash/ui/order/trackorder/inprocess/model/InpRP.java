package com.saidur.stylesplash.ui.order.trackorder.inprocess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InpRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<InpModel> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InpModel> getData() {
        return data;
    }

    public void setData(List<InpModel> data) {
        this.data = data;
    }
}
