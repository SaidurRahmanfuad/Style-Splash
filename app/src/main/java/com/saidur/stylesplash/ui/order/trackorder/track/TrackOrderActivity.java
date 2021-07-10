package com.saidur.stylesplash.ui.order.trackorder.track;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleData;
import com.saidur.stylesplash.utils.ApiService;
import com.saidur.stylesplash.utils.Const;
import com.saidur.stylesplash.utils.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackOrderActivity extends AppCompatActivity implements ClickInterface{
    ProgressDialog pd;
    EditText searchbox;
    ImageView searchbtn;
    String Status, CustName, CustPhn, CustAdd, OrderId;
    CardView orderprocess, order_onprocess2, ordercancel, ordersale, custinfo, track_noorder;
    LinearLayout tracklin;
    TextView custname, custphn, custadd, orderid;
    TextView cancelDate, inproDate, saleDate, cancelTime, inproTime, saleTime;
    List<TrackData> oids;
    TrackidAdapter trackidAdapter;
    RecyclerView rv_trackid;
    OrderListVM orderListVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);
        orderListVM = new ViewModelProvider(TrackOrderActivity.this).get(OrderListVM.class);

        pd = new ProgressDialog(TrackOrderActivity.this);
        pd.setMessage("Searching..");
        pd.setCancelable(false);

        initView();
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numb = searchbox.getText().toString();
                // trackApiCall(oidz);
                trackIdApiCall(numb);

                    custinfo.setVisibility(View.GONE);
                    tracklin.setVisibility(View.GONE);
                    orderprocess.setVisibility(View.GONE);
                    ordercancel.setVisibility(View.GONE);
                    track_noorder.setVisibility(View.GONE);
                    ordersale.setVisibility(View.GONE);
                    order_onprocess2.setVisibility(View.GONE);

            }
        });

        orderListVM.StatusrObserver().observe(this, new Observer<TrackData>() {
            @Override
            public void onChanged(TrackData trackData) {
               // Toast.makeText(TrackOrderActivity.this, "obsrvstatus call" + trackData, Toast.LENGTH_SHORT).show();
                if (trackData != null) {
                    Status = trackData.getStatus();
                    //Toast.makeText(TrackOrderActivity.this, "obsrvstatus" + Status, Toast.LENGTH_SHORT).show();
                    orderid.setText(trackData.getoCode());
                    custname.setText(trackData.getCustomerName());
                    custphn.setText(trackData.getMobile());
                    custadd.setText(trackData.getAddress());

                    if (Status.equals("5")) {
                        custinfo.setVisibility(View.VISIBLE);
                        tracklin.setVisibility(View.VISIBLE);
                        orderprocess.setVisibility(View.VISIBLE);
                        ordercancel.setVisibility(View.VISIBLE);

                        track_noorder.setVisibility(View.GONE);
                        ordersale.setVisibility(View.GONE);
                        order_onprocess2.setVisibility(View.GONE);
                    }
                    if (Status.equals("2")) {
                        custinfo.setVisibility(View.VISIBLE);
                        tracklin.setVisibility(View.VISIBLE);

                        order_onprocess2.setVisibility(View.VISIBLE);
                        ordersale.setVisibility(View.VISIBLE);

                        ordercancel.setVisibility(View.GONE);
                        track_noorder.setVisibility(View.GONE);
                        orderprocess.setVisibility(View.GONE);
                    }
                    if (Status.equals("1")) {
                        custinfo.setVisibility(View.VISIBLE);
                        tracklin.setVisibility(View.VISIBLE);
                        orderprocess.setVisibility(View.VISIBLE);

                        ordercancel.setVisibility(View.GONE);
                        track_noorder.setVisibility(View.GONE);
                        ordersale.setVisibility(View.GONE);
                        order_onprocess2.setVisibility(View.GONE);
                    }
                } else {
                    track_noorder.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void initView() {
        searchbox = findViewById(R.id.searchbox);
        searchbtn = findViewById(R.id.searchbtn);

        orderid = findViewById(R.id.trackid);
        custname = findViewById(R.id.custname);
        custphn = findViewById(R.id.custphn);
        custadd = findViewById(R.id.custadd);

        orderprocess = findViewById(R.id.order_onprocess);
        order_onprocess2 = findViewById(R.id.order_onprocess2);
        ordercancel = findViewById(R.id.track_cancle);
        ordersale = findViewById(R.id.track_sale);
        custinfo = findViewById(R.id.order_custinfo);
        tracklin = findViewById(R.id.tracklin);
        track_noorder = findViewById(R.id.track_noorder);

        cancelDate = findViewById(R.id.cancelDate);
        inproDate = findViewById(R.id.inproDate);
        saleDate = findViewById(R.id.saleDate);
        rv_trackid = findViewById(R.id.oids);
    }

    public void trackApiCall(String oid) {
        pd.show();
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<TrackRP> call = apiService.getTrackOrder(oid);
        call.enqueue(new Callback<TrackRP>() {
            @Override
            public void onResponse(Call<TrackRP> call, Response<TrackRP> response) {

                if (response.isSuccessful()) {
                    pd.dismiss();
                    if (response.body().getMessage().equals("Order Track found by Order Number")) {
                        TrackData td = response.body().getData();
                        // String inprocessdate=td.getoDate();
                        OrderId = td.getoCode();
                        Status = td.getStatus();
                        CustName = td.getCustomerName();
                        CustPhn = td.getMobile();
                        CustAdd = td.getAddress();

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
                        if (Status.equals("2")) {
                            custinfo.setVisibility(View.VISIBLE);
                            tracklin.setVisibility(View.VISIBLE);

                            order_onprocess2.setVisibility(View.VISIBLE);
                            ordersale.setVisibility(View.VISIBLE);

                            ordercancel.setVisibility(View.GONE);
                            track_noorder.setVisibility(View.GONE);
                            orderprocess.setVisibility(View.GONE);
                        }
                        if (Status.equals("1")) {
                            custinfo.setVisibility(View.VISIBLE);
                            tracklin.setVisibility(View.VISIBLE);
                            orderprocess.setVisibility(View.VISIBLE);

                            ordercancel.setVisibility(View.GONE);
                            track_noorder.setVisibility(View.GONE);
                            ordersale.setVisibility(View.GONE);
                            order_onprocess2.setVisibility(View.GONE);
                        }
                    } else {
                        track_noorder.setVisibility(View.VISIBLE);
                    }


                } else {
                    pd.dismiss();
                    track_noorder.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TrackRP> call, Throwable t) {
                pd.dismiss();
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    private void trackIdApiCall(String oid) {
        pd.show();
        //oids=new ArrayList<>();
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<TrackIdRP> call = apiService.getTrackOrderId(oid);

        call.enqueue(new Callback<TrackIdRP>() {
            @Override
            public void onResponse(Call<TrackIdRP> call, Response<TrackIdRP> response) {

                if (response.isSuccessful()) {
                    pd.dismiss();
                    if (response.body().getMessage().equals("Order Track found by Mobile Number")) {
                        oids = response.body().getData();

                        // trackidAdapter.setTrackidList(oids);
                        trackidAdapter = new TrackidAdapter(TrackOrderActivity.this, oids,TrackOrderActivity.this);
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(TrackOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);

                        rv_trackid.setLayoutManager(layoutManager);
                        rv_trackid.setAdapter(trackidAdapter);
                    } else {
                        track_noorder.setVisibility(View.VISIBLE);
                    }


                } else {
                    pd.dismiss();
                    track_noorder.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TrackIdRP> call, Throwable t) {
                pd.dismiss();
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }


    @Override
    public void onItemClick(int position, String code) {
        Toast.makeText(this, "position "+position, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "code "+code, Toast.LENGTH_SHORT).show();
        trackApiCall(code);
    }
}