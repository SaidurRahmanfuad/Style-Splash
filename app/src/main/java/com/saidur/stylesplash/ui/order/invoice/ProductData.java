package com.saidur.stylesplash.ui.order.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductData {

    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("oPrice")
    @Expose
    private String oPrice;
    @SerializedName("oQnt")
    @Expose
    private String oQnt;
    @SerializedName("tPrice")
    @Expose
    private String tPrice;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productcode")
    @Expose
    private String productcode;

    public ProductData() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getoPrice() {
        return oPrice;
    }

    public void setoPrice(String oPrice) {
        this.oPrice = oPrice;
    }

    public String getoQnt() {
        return oQnt;
    }

    public void setoQnt(String oQnt) {
        this.oQnt = oQnt;
    }

    public String gettPrice() {
        return tPrice;
    }

    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }
}
