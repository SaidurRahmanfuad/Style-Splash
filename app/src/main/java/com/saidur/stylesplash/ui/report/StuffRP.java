package com.saidur.stylesplash.ui.report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saidur.stylesplash.ui.report.model.Stuff;

import java.util.List;

public class StuffRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Stuff> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Stuff> getData() {
        return data;
    }

    public void setData(List<Stuff> data) {
        this.data = data;
    }

}