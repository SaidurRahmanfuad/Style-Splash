package com.saidur.stylesplash.ui.customer;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.customer.network.CustomerRP;
import com.saidur.stylesplash.utils.ApiService;
import com.saidur.stylesplash.utils.RetrofitInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustListVM extends AndroidViewModel {
    private MutableLiveData<List<CustomerListData>> CustList;
    private MutableLiveData<List<CustomerListData>> fromdata;

    private MutableLiveData<String> SaveCust;
    private MutableLiveData<String> CustloadDone;


    public CustListVM(@NonNull Application application) {
        super(application);
        CustList = new MutableLiveData<>();
        SaveCust = new MutableLiveData<>();
        CustloadDone = new MutableLiveData<>();

        fromdata = new MutableLiveData<>();
    }

    public MutableLiveData<List<CustomerListData>> getAllcustObserver() {
        return CustList;
    }

    public MutableLiveData<String> savecustObserver() {
        return SaveCust;
    }

    public MutableLiveData<String> CustloadDoneObserver() {
        return CustloadDone;
    }

    public void AllCustApiCall(String from) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<CustomerRP> call = apiService.getAllCustomer();
        call.enqueue(new Callback<CustomerRP>() {
            @Override
            public void onResponse(Call<CustomerRP> call, Response<CustomerRP> response) {
                if (response.isSuccessful()) {

                  //  CustList.setValue(response.body().getData());
                    List<CustomerListData> cldata = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().size(); i++) {

                        String id = response.body().getData().get(i).getCustomerID();
                        String name = response.body().getData().get(i).getCustomerName();
                        String mob = response.body().getData().get(i).getMobile();
                        String add = response.body().getData().get(i).getAddress();
                        CustomerListData clobj=new CustomerListData();
                        clobj.setCustomerID(id);
                        clobj.setCustomerName(name);
                        clobj.setMobile(mob);
                        clobj.setAddress(add);
                        clobj.setFrom(from);

                        cldata.add(clobj);

                    }
                    CustList.setValue(cldata);

                    Log.d("fromcheck", "onResponse: " + from);
                    CustloadDone.setValue("lcomplete");

                } else {
                    CustList.setValue(null);
                    CustloadDone.setValue("lincomplete");
                }
            }

            @Override
            public void onFailure(Call<CustomerRP> call, Throwable t) {
                CustList.setValue(null);
                CustloadDone.setValue("failed");
                Log.d("Cust Activity", "getCause: " + t.getCause());
                Log.d("Cust Activity", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

    public void SaveCustApiCall(HashMap<String, String> map) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<String> call = apiService.saveCustomer(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    SaveCust.setValue("Ok");
                } else {
                    SaveCust.setValue("Ok");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                SaveCust.setValue("Ok");
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });

    }
}
