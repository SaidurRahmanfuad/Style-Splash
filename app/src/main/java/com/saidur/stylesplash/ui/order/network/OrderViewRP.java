package com.saidur.stylesplash.ui.order.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saidur.stylesplash.ui.order.invoice.ProductData;

import java.util.List;

public class OrderViewRP {
/*    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private OrderListData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderListData getData() {
        return data;
    }

    public void setData(OrderListData data) {
        this.data = data;
    }*/

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private OrderListData data;
    @SerializedName("product")
    @Expose
    private List<ProductData> product = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderListData getData() {
        return data;
    }

    public void setData(OrderListData data) {
        this.data = data;
    }

    public List<ProductData> getProduct() {
        return product;
    }

    public void setProduct(List<ProductData> product) {
        this.product = product;
    }
}
