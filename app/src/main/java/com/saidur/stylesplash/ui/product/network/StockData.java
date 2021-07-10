package com.saidur.stylesplash.ui.product.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockData {
    @SerializedName("stockID")
    @Expose
    private String stockID;
    @SerializedName("compid")
    @Expose
    private String compid;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("totalPices")
    @Expose
    private String totalPices;
    @SerializedName("dtquantity")
    @Expose
    private String dtquantity;


    @SerializedName("regby")
    @Expose
    private String regby;
    @SerializedName("regdate")
    @Expose
    private String regdate;
    @SerializedName("upby")
    @Expose
    private String upby;
    @SerializedName("update")
    @Expose
    private String update;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productcode")
    @Expose
    private String productcode;
    @SerializedName("pprice")
    @Expose
    private String pprice;
    @SerializedName("sprice")
    @Expose
    private String sprice;

    private String subtotal;
    public StockData() {

    }

    public StockData(String product,String productName, String sprice,String totalPices,/*String dtquantity,*/ String subtotal) {
        this.product = product;
        this.productName = productName;
        this.sprice = sprice;
        this.totalPices = totalPices;
        //this.dtquantity = dtquantity;
        this.subtotal = subtotal;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public StockData(String stockID, String compid, String product, String totalPices, String regby, String regdate, String upby, String update, String productName, String productcode, String pprice, String sprice) {
        this.stockID = stockID;
        this.compid = compid;
        this.product = product;
        this.totalPices = totalPices;
        this.regby = regby;
        this.regdate = regdate;
        this.upby = upby;
        this.update = update;
        this.productName = productName;
        this.productcode = productcode;
        this.pprice = pprice;
        this.sprice = sprice;
    }

    public String getDtquantity() {
        return dtquantity;
    }

    public void setDtquantity(String dtquantity) {
        this.dtquantity = dtquantity;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTotalPices() {
        return totalPices;
    }

    public void setTotalPices(String totalPices) {
        this.totalPices = totalPices;
    }

    public String getRegby() {
        return regby;
    }

    public void setRegby(String regby) {
        this.regby = regby;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getUpby() {
        return upby;
    }

    public void setUpby(String upby) {
        this.upby = upby;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
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

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    @Override
    public String toString() {
        return  productName;
    }
}
