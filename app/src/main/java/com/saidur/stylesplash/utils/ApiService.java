package com.saidur.stylesplash.utils;

import com.saidur.stylesplash.ui.customer.network.CustomerRP;
import com.saidur.stylesplash.ui.login.network.LoginRP;
import com.saidur.stylesplash.ui.order.AddOrderRP;
import com.saidur.stylesplash.ui.order.invoice.ProductRP;
import com.saidur.stylesplash.ui.order.network.OrderListRP;
import com.saidur.stylesplash.ui.order.network.OrderViewRP;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleRP;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.model.InpRP;
import com.saidur.stylesplash.ui.order.trackorder.track.TrackIdRP;
import com.saidur.stylesplash.ui.order.trackorder.track.TrackRP;
import com.saidur.stylesplash.ui.product.network.StockRP;


import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    //login(done)
    @FormUrlEncoded
    @POST("Authapi/post_login_user_data")
    Call<LoginRP> loginsave(@FieldMap Map<String, String> login);

    //Done--get all orderlist
    @GET("Authapi/get_order_data")
    Call<OrderListRP> getAllOrder();

    //Done--get view orderlist
    @GET("Authapi/get_view_order_data/{oid}")
    Call<OrderViewRP> getViewOrderInv(@Path("oid") String oid);

    //track order --Done (Time date formate baki)
    @GET("Authapi/get_order_track_data/{oid}")
    Call<TrackRP> getTrackOrder(@Path("oid") String oid);

    @GET("Authapi/get_order_mtrack_data/{oid}")
    Call<TrackIdRP> getTrackOrderId(@Path("oid") String oid);
    //Done--delete order
    @POST("Authapi/post_order_delete_data/{oid}")
    Call<String> deleteOrder(@Path("oid") String oid);

    //Done--cancle order
    @POST("Authapi/post_order_cancel_data/{oid}")
    Call<String> cancleOrder(@Path("oid") String oid);

    //Done--cancle order list
    @GET("Authapi/get_cancel_order_data")
    Call<CancleRP> cancelOrderList();

    //Done--inprocess order list
    @GET("Authapi/get_inprogress_order_data")
    Call<InpRP> inproceessOrderList();

    //Done--sale order list
    @GET("Authapi/get_sale_order_data")
    Call<CancleRP> saleOrderList();

    //working--product order wise
    @GET("Authapi/get_order_product_data/{oid}")
    Call<ProductRP> getProducts(@Path("oid") String oid);

    //Done--sale order note (after click sale--post_order_sale) kaj korte hobe
    @FormUrlEncoded
    @POST("Authapi/post_save_order_data")
    Call<AddOrderRP> saleOrder(@FieldMap Map<String, String> params,
                               @Field("product[]") ArrayList<String> productID,
                               @Field("price[]") ArrayList<String> sprice,
                               @Field("quantity[]") ArrayList<String> quantity,
                               @Field("totalPrice[]") ArrayList<String> totalPrice);
    //After click sell--done
    @FormUrlEncoded
    @POST("Authapi/post_order_sale")
    Call<String> saleSave(@FieldMap Map<String, String> params,
                               @Field("productID[]") ArrayList<String> productID,
                               @Field("sprice[]") ArrayList<String> sprice,
                               @Field("quantity[]") ArrayList<String> quantity,
                               @Field("totalPrice[]") ArrayList<String> totalPrice);

    //Done--getall customer list
    @GET("Authapi/get_customer_data")
    Call<CustomerRP> getAllCustomer();

    //Done get all product list
    @GET("Authapi/get_product_stock_data")
    Call<StockRP> getAllStock();

  /*  //Done get all product list
    @GET("Authapi/get_fresh_product_stock_data")
    Call<StockRP> get_FreshStock();
    //Done get all product list
    @GET("Authapi/get_damage_product_stock_data")
    Call<StockRP> get_damageStock();*/

    //Done save customer
    @FormUrlEncoded
    @POST("Authapi/post_save_customer_data")
    Call<String> saveCustomer(@FieldMap Map<String, String> savecust);

}
