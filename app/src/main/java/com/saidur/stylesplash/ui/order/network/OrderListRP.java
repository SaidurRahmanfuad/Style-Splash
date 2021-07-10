package com.saidur.stylesplash.ui.order.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderListRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<OrderListData> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderListData> getData() {
        return data;
    }

    public void setData(List<OrderListData> data) {
        this.data = data;
    }
}
