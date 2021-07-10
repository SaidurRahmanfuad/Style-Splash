package com.saidur.stylesplash.ui.order.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductRP {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product")
    @Expose
    private List<ProductData> product = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductData> getProduct() {
        return product;
    }

    public void setProduct(List<ProductData> product) {
        this.product = product;
    }
}
