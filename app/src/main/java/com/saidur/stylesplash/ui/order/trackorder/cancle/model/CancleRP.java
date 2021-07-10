package com.saidur.stylesplash.ui.order.trackorder.cancle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CancleRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<CancleData> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CancleData> getData() {
        return data;
    }

    public void setData(List<CancleData> data) {
        this.data = data;
    }
}
