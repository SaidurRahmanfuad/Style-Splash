package com.saidur.stylesplash.ui.order.trackorder.track;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private TrackData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TrackData getData() {
        return data;
    }

    public void setData(TrackData data) {
        this.data = data;
    }
}
