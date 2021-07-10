package com.saidur.stylesplash.ui.login.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class LoginRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private LoginData data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
