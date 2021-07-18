package com.saidur.stylesplash.ui.report;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.saidur.stylesplash.ui.report.model.SorData;
import com.saidur.stylesplash.ui.report.model.SorTotal;
import com.saidur.stylesplash.ui.report.model.Stuff;
import com.saidur.stylesplash.utils.ApiService;
import com.saidur.stylesplash.utils.RetrofitInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuffVM extends AndroidViewModel {
    public MutableLiveData<List<Stuff>> getAllStuff;
    public MutableLiveData<List<SorData>> getStuffAllOrder;

    public MutableLiveData <SorTotal> counters;

    public StuffVM(@NonNull Application application) {
        super(application);
        getAllStuff=new MutableLiveData<>();
        getStuffAllOrder=new MutableLiveData<>();
        counters=new MutableLiveData<>();
    }

    public MutableLiveData<List<Stuff>> allStuffObserver()
    {
        return getAllStuff;
    }
    public MutableLiveData<List<SorData>> StuffAllOrderObserver()
    {
        return getStuffAllOrder;
    }
    public MutableLiveData<SorTotal> StuffCounterObserver()
    {
        return counters;
    }

    public void AllStuffApiCall() {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<StuffRP> call = apiService.getAllStuff();
        call.enqueue(new Callback<StuffRP>() {
            @Override
            public void onResponse(Call<StuffRP> call, Response<StuffRP> response) {
                if (response.isSuccessful() && response.body().getMessage().equals("Staf found")) {
                    getAllStuff.setValue(response.body().getData());
                } else {
                    getAllStuff.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<StuffRP> call, Throwable t) {
                getAllStuff.setValue(null);
                Log.d("Cust Activity", "getCause: " + t.getCause());
                Log.d("Cust Activity", "getStackTrace: " + t.getStackTrace());

            }
        });

    }
    public void onlyStuffReportApiCall(HashMap<String,String>map) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<SorRP> call = apiService.getStuffOReport(map);
        call.enqueue(new Callback<SorRP>() {
            @Override
            public void onResponse(Call<SorRP> call, Response<SorRP> response) {
                if (response.isSuccessful() && response.body().getMessage().equals("Order found")) {

                    SorTotal mcounter=new SorTotal();
                    mcounter.setTorder(response.body().getTorder());
                    mcounter.setSorder(response.body().getSorder());
                    mcounter.setPorder(response.body().getPorder());
                    mcounter.setCorder(response.body().getCorder());

                    counters.setValue(mcounter);


                    getStuffAllOrder.setValue(response.body().getData());
                } else {
                    getStuffAllOrder.setValue(null);
                    counters.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<SorRP> call, Throwable t) {
                getAllStuff.setValue(null);
                Log.d("Cust Activity", "getCause: " + t.getCause());
                Log.d("Cust Activity", "getStackTrace: " + t.getStackTrace());

            }
        });

    }
}
