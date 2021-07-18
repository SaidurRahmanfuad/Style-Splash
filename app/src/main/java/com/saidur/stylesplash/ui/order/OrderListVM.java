package com.saidur.stylesplash.ui.order;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.saidur.stylesplash.ui.order.adapter.AllOrderAdapter;
import com.saidur.stylesplash.ui.order.invoice.ProductData;
import com.saidur.stylesplash.ui.order.invoice.ProductRP;
import com.saidur.stylesplash.ui.order.network.OrderListData;
import com.saidur.stylesplash.ui.order.network.OrderListRP;
import com.saidur.stylesplash.ui.order.network.OrderViewRP;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleData;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleRP;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.model.InpModel;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.model.InpRP;
import com.saidur.stylesplash.ui.order.trackorder.track.TrackData;
import com.saidur.stylesplash.ui.order.trackorder.track.TrackRP;
import com.saidur.stylesplash.ui.product.network.StockData;
import com.saidur.stylesplash.ui.product.network.StockRP;
import com.saidur.stylesplash.utils.ApiService;
import com.saidur.stylesplash.utils.RetrofitInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListVM extends AndroidViewModel {
    private MutableLiveData<List<OrderListData>> OrderList;
    private MutableLiveData<OrderListData> OrderView;
    private MutableLiveData<List<ProductData>> ProductView;

    private MutableLiveData<List<InpModel>> InprocessOrderList;
    private MutableLiveData<List<CancleData>> CancelOrderList;
    private MutableLiveData<List<CancleData>> SaleOrderList;
    private MutableLiveData<List<StockData>> StockList;
    private MutableLiveData<List<StockData>> DStockList;
    private MutableLiveData<String> StockloadDone;
    private MutableLiveData<String> Deletemsg;
    private MutableLiveData<String> AdorderOid;
    private MutableLiveData<String> allorderloadDone;
    private MutableLiveData<TrackData> Status;


    public OrderListVM(@NonNull Application application) {
        super(application);
        OrderList = new MutableLiveData<>();
        StockList = new MutableLiveData<>();
        DStockList = new MutableLiveData<>();
        Deletemsg = new MutableLiveData<>();
        InprocessOrderList = new MutableLiveData<>();
        CancelOrderList = new MutableLiveData<>();
        SaleOrderList = new MutableLiveData<>();
        AdorderOid = new MutableLiveData<>();

        OrderView = new MutableLiveData<>();
        ProductView = new MutableLiveData<>();
        StockloadDone = new MutableLiveData<>();
        allorderloadDone = new MutableLiveData<>();
        Status = new MutableLiveData<>();

    }

    public MutableLiveData<List<OrderListData>> getAllOrderObserver() {
        return OrderList;
    }
    public MutableLiveData<String> AllOrderloadDoneObserver() {
        return allorderloadDone;
    }

    public MutableLiveData<OrderListData> getOrderViewObserver() {
        return OrderView;
    }

    public MutableLiveData<List<ProductData>> getProductViewObserver() {
        return ProductView;
    }

    public MutableLiveData<List<InpModel>> getInprocessOrderListObserver() {
        return InprocessOrderList;
    }

    public MutableLiveData<List<CancleData>> getCancleOrderListObserver() {
        return CancelOrderList;
    }

    public MutableLiveData<List<CancleData>> getSaleOrderListObserver() {
        return SaleOrderList;
    }

    public MutableLiveData<List<StockData>> getAllStockObserver() {
        return StockList;
    }
    public MutableLiveData<List<StockData>> getDamageStockObserver() {
        return DStockList;
    }
    public MutableLiveData<String> StockloadDoneObserver() {
        return StockloadDone;
    }

    public MutableLiveData<String> deleteOrderObserver() {
        return Deletemsg;
    }

    public MutableLiveData<String> adorderOidrObserver() {
        return AdorderOid;
    }

    public MutableLiveData<TrackData> StatusrObserver() {
        return Status;
    }


    public void AllOrderApiCall() {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<OrderListRP> call = apiService.getAllOrder();
        call.enqueue(new Callback<OrderListRP>() {
            @Override
            public void onResponse(Call<OrderListRP> call, Response<OrderListRP> response) {
                if (response.isSuccessful()) {
                    OrderList.setValue(response.body().getData());
                    allorderloadDone.setValue("ok");
                } else {
                    OrderList.setValue(null);
                    allorderloadDone.setValue("notok");
                }
            }

            @Override
            public void onFailure(Call<OrderListRP> call, Throwable t) {
                OrderList.setValue(null);
                allorderloadDone.setValue("failed");
                Log.d("Login Activity", "getCause: " + t.getCause());
                Log.d("Login Activity", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void OrdergetInprocessListApiCall() {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<InpRP> call = apiService.inproceessOrderList();
        call.enqueue(new Callback<InpRP>() {
            @Override
            public void onResponse(Call<InpRP> call, Response<InpRP> response) {

                if (response.isSuccessful()) {
                    InprocessOrderList.setValue(response.body().getData());
                } else {
                    InprocessOrderList.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<InpRP> call, Throwable t) {
                InprocessOrderList.setValue(null);
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void OrdergetCancleListApiCall() {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<CancleRP> call = apiService.cancelOrderList();
        call.enqueue(new Callback<CancleRP>() {
            @Override
            public void onResponse(Call<CancleRP> call, Response<CancleRP> response) {

                if (response.isSuccessful()) {
                    CancelOrderList.setValue(response.body().getData());
                } else {
                    CancelOrderList.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CancleRP> call, Throwable t) {
                CancelOrderList.setValue(null);
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void OrdergetSaleListApiCall() {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<CancleRP> call = apiService.saleOrderList();
        call.enqueue(new Callback<CancleRP>() {
            @Override
            public void onResponse(Call<CancleRP> call, Response<CancleRP> response) {

                if (response.isSuccessful()) {
                    SaleOrderList.setValue(response.body().getData());
                } else {
                    SaleOrderList.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CancleRP> call, Throwable t) {
                SaleOrderList.setValue(null);
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void OrderViewApiCall(String oid) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<OrderViewRP> call = apiService.getViewOrderInv(oid);
        call.enqueue(new Callback<OrderViewRP>() {
            @Override
            public void onResponse(Call<OrderViewRP> call, Response<OrderViewRP> response) {
                if (response.isSuccessful()) {


                    OrderView.setValue(response.body().getData());
                    ProductView.setValue(response.body().getProduct());

                  /*  ApiService apiServices = RetrofitInstance.getRetroClient().create(ApiService.class);
                    Call<ProductRP> callp = apiServices.getProducts(oid);
                    callp.enqueue(new Callback<ProductRP>() {
                        @Override
                        public void onResponse(Call<ProductRP> call, Response<ProductRP> response) {
                            Toast.makeText(getApplication(), "response "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            if (response.isSuccessful()) {


                                ProductView.setValue(response.body().getProduct());
                                // ViewOrder.setValue("vieworder");
                            } else {
                                ProductView.setValue(null);
                            }
                        }

                        @Override
                        public void onFailure(Call<ProductRP> call, Throwable t) {
                            ProductView.setValue(null);

                            Log.d("txtFragment", "getCause: " + t.getCause());
                            Log.d("txtFragment", "getStackTrace: " + t.getStackTrace());

                        }
                    });
*/


                    // ViewOrder.setValue("vieworder");
                } else {
                    OrderView.setValue(null);
                    ProductView.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<OrderViewRP> call, Throwable t) {
                OrderView.setValue(null);
                ProductView.setValue(null);

                Log.d("txtFragment", "getCause: " + t.getCause());
                Log.d("txtFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void ProductrViewApiCall(String oid) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<ProductRP> call = apiService.getProducts(oid);
        call.enqueue(new Callback<ProductRP>() {
            @Override
            public void onResponse(Call<ProductRP> call, Response<ProductRP> response) {
                Toast.makeText(getApplication(), "response " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {


                    ProductView.setValue(response.body().getProduct());
                    // ViewOrder.setValue("vieworder");
                } else {
                    ProductView.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProductRP> call, Throwable t) {
                ProductView.setValue(null);

                Log.d("txtFragment", "getCause: " + t.getCause());
                Log.d("txtFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void OrderCancleApiCall(String oid) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<String> call = apiService.cancleOrder(oid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    Deletemsg.setValue("delet");
                } else {
                    Deletemsg.setValue("notdelet");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Deletemsg.setValue("notdelet");
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void OrderDeleteApiCall(String oid) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<String> call = apiService.deleteOrder(oid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    Deletemsg.setValue("delet");
                } else {
                    Deletemsg.setValue("notdelet");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Deletemsg.setValue("notdelet");
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void AllStockApiCall() {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<StockRP> call = apiService.getAllStock();
        call.enqueue(new Callback<StockRP>() {
            @Override
            public void onResponse(Call<StockRP> call, Response<StockRP> response) {
                if (response.isSuccessful()) {
                    StockList.setValue(response.body().getData());
                    StockloadDone.setValue("done");
                } else {
                    StockList.setValue(null);
                    StockloadDone.setValue("notdone");
                }
            }

            @Override
            public void onFailure(Call<StockRP> call, Throwable t) {
                StockList.setValue(null);
                StockloadDone.setValue("failed");
                Log.d("Login Activity", "getCause: " + t.getCause());
                Log.d("Login Activity", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void AllDamageStockApiCall() {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<StockRP> call = apiService.getAllStock();
        call.enqueue(new Callback<StockRP>() {
            @Override
            public void onResponse(Call<StockRP> call, Response<StockRP> response) {
                if (response.isSuccessful()) {
                    DStockList.setValue(response.body().getData());
                    StockloadDone.setValue("done");
                } else {
                    DStockList.setValue(null);
                    StockloadDone.setValue("notdone");
                }
            }

            @Override
            public void onFailure(Call<StockRP> call, Throwable t) {
                DStockList.setValue(null);
                StockloadDone.setValue("failed");
                Log.d("Login Activity", "getCause: " + t.getCause());
                Log.d("Login Activity", "getStackTrace: " + t.getStackTrace());

            }
        });

    }
    public void SaveOrderApiCall(HashMap<String, String> map, ArrayList<String> productID, ArrayList<String> sprice,
                                 ArrayList<String> quantity, ArrayList<String> totalPrice) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<AddOrderRP> call = apiService.saleOrder(map, productID, sprice, quantity, totalPrice);
        call.enqueue(new Callback<AddOrderRP>() {
            @Override
            public void onResponse(Call<AddOrderRP> call, Response<AddOrderRP> response) {

                if (response.isSuccessful()||response.body().getMessage().equals("Order added Success")) {
                    //Toast.makeText(getApplication(), "oidapi "+response.body().toString(), Toast.LENGTH_SHORT).show();
                    AdorderOid.setValue(String.valueOf(response.body().data));
                } else {
                    AdorderOid.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddOrderRP> call, Throwable t) {
                AdorderOid.setValue(null);
                Log.d("vv", "getCause: " + t.getCause());
                Log.d("vv", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void SaveSaleApiCall(HashMap<String, String> map, String oid) {


      //  ArrayList<String> products=new ArrayList<>();

        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<ProductRP> call1 = apiService.getProducts(oid);
        call1.enqueue(new Callback<ProductRP>() {
            @Override
            public void onResponse(Call<ProductRP> call, Response<ProductRP> response) {
                if(response.isSuccessful())
                {
                    ArrayList<String> productID=new ArrayList<>();
                    ArrayList<String> sprice=new ArrayList<>();
                    ArrayList<String> quantity=new ArrayList<>();
                    ArrayList<String> totalPrice=new ArrayList<>();
                    //sprice=new ArrayList<>();
                   //ProductData pd= response.body().getProduct();
                   for(ProductData pd:response.body().getProduct())
                   {
                      productID.add(pd.getProduct());
                       sprice.add(pd.getoPrice());
                       quantity.add(pd.getoQnt());
                       totalPrice.add(pd.gettPrice());


                   }

                    Log.d("vmres", "onResponse productID: "+productID);
                    Log.d("vmres", "onResponse sprice: "+sprice);
                    Log.d("vmres", "onResponse map: "+map.toString());
                    Log.d("vmres", "onResponse map: "+map.toString());
                    Call<String> call2 = apiService.saleSave(map, productID, sprice, quantity, totalPrice);
                    call2.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            if (response.isSuccessful()) {
                                OrderViewApiCall(oid);
                            } else {
                              //  AdorderOid.setValue(null);
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            AdorderOid.setValue(null);
                            Log.d("vv", "getCause: " + t.getCause());
                            Log.d("vv", "getStackTrace: " + t.getStackTrace());

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ProductRP> call, Throwable t) {

            }
        });




    }

    public void trackApiCall(String oid) {
       // pd.show();
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<TrackRP> call = apiService.getTrackOrder(oid);
        call.enqueue(new Callback<TrackRP>() {
            @Override
            public void onResponse(Call<TrackRP> call, Response<TrackRP> response) {


                  //  pd.dismiss();
                    if(response.isSuccessful() && response.body().getMessage().equals("Order Track found by Order Number"))
                    {
                       // TrackData td = response.body().getData();
                        Status.setValue(response.body().getData());
                        // String inprocessdate=td.getoDate();
                      /*  OrderId=td.getoCode();
                        Status=td.getStatus();
                        CustName=td.getCustomerName();
                        CustPhn=td.getMobile();
                        CustAdd=td.getAddress();

                        orderid.setText(OrderId);
                        custname.setText(CustName);
                        custphn.setText(CustPhn);
                        custadd.setText(CustAdd);

                        if (Status.equals("5")) {
                            custinfo.setVisibility(View.VISIBLE);
                            tracklin.setVisibility(View.VISIBLE);
                            orderprocess.setVisibility(View.VISIBLE);
                            ordercancel.setVisibility(View.VISIBLE);

                            track_noorder.setVisibility(View.GONE);
                            ordersale.setVisibility(View.GONE);
                            order_onprocess2.setVisibility(View.GONE);
                        }
                        if(Status.equals("2"))
                        {
                            custinfo.setVisibility(View.VISIBLE);
                            tracklin.setVisibility(View.VISIBLE);

                            order_onprocess2.setVisibility(View.VISIBLE);
                            ordersale.setVisibility(View.VISIBLE);

                            ordercancel.setVisibility(View.GONE);
                            track_noorder.setVisibility(View.GONE);
                            orderprocess.setVisibility(View.GONE);
                        }
                        if(Status.equals("1"))
                        {
                            custinfo.setVisibility(View.VISIBLE);
                            tracklin.setVisibility(View.VISIBLE);
                            orderprocess.setVisibility(View.VISIBLE);

                            ordercancel.setVisibility(View.GONE);
                            track_noorder.setVisibility(View.GONE);
                            ordersale.setVisibility(View.GONE);
                            order_onprocess2.setVisibility(View.GONE);
                        }*/
                    }
                    else {
                        Status.setValue(null);
                    }

            }

            @Override
            public void onFailure(Call<TrackRP> call, Throwable t) {
                Status.setValue(null);
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }
}
