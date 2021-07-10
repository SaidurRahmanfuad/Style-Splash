package com.saidur.stylesplash.ui.order.trackorder.track;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackIdRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<TrackData> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TrackData> getData() {
        return data;
    }

    public void setData(List<TrackData> data) {
        this.data = data;
    }
}
