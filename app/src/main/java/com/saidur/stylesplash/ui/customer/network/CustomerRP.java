package com.saidur.stylesplash.ui.customer.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<CustomerListData> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CustomerListData> getData() {
        return data;
    }

    public void setData(List<CustomerListData> data) {
        this.data = data;
    }
}
