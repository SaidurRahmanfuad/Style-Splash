package com.saidur.stylesplash.ui.login;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.saidur.stylesplash.ui.login.network.LoginData;
import com.saidur.stylesplash.ui.login.network.LoginRP;
import com.saidur.stylesplash.utils.ApiService;
import com.saidur.stylesplash.utils.RetrofitInstance;
import com.saidur.stylesplash.utils.Session;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginVM extends AndroidViewModel {
   public MutableLiveData <LoginData> loginUserData;
   Session sessionManagement;
    public LoginVM(@NonNull Application application) {
        super(application);
        loginUserData=new MutableLiveData<>();
    }
   public MutableLiveData <LoginData>getLoginUserDataObserver()
   {
       return loginUserData;
   }
    public void LoginApiCall(HashMap<String,String>loginmap) {
        ApiService apiService = RetrofitInstance.getRetroClient().create(ApiService.class);
        Call<LoginRP> call = apiService.loginsave(loginmap);
        call.enqueue(new Callback<LoginRP>() {
            @Override
            public void onResponse(Call<LoginRP> call, Response<LoginRP> response) {
                if (response.isSuccessful() && response.body().getMessage().equals("User found")) {
                   // loginUserData.setValue(response.body().getData());
                    LoginData logUserData=response.body().getData();

                    loginUserData.setValue(response.body().getData());
                    Log.d("test","uidsaa : "+logUserData.getUid());

                    sessionManagement = new Session(getApplication());
                    sessionManagement.saveSessaion(logUserData.getUid(), logUserData.getEmpid(), logUserData.getCompid(), logUserData.getCompname(),
                            logUserData.getName(), logUserData.getMobile(), logUserData.getEmail(), logUserData.getUserrole());
                    Toast.makeText(getApplication(), ""+ logUserData.getName(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplication(), "User role after "+ logUserData.getUserrole(), Toast.LENGTH_SHORT).show();
                   // loginUserData.getValue().get(0).getCompid();
                   // sessionManagement.saveSessaion(user.getUid(), user.getEmpid(), user.getName(), user.getMobile(),user.getUserrole());
                } else {
                    loginUserData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginRP> call, Throwable t) {
                loginUserData.setValue(null);
                Log.d("Login Activity", "getCause: " + t.getCause());
                Log.d("Login Activity", "getStackTrace: " + t.getStackTrace());

            }
        });

    }

}
