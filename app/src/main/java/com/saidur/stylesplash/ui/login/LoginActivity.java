package com.saidur.stylesplash.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.saidur.stylesplash.MainActivity;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.login.network.LoginData;
import com.saidur.stylesplash.utils.Session;

import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button user_login_btn;
    private TextInputEditText dManLoginId, dManLoginPass;
    private TextInputLayout user_loginId_TIL, user_pass_TIL;
    //user input
    String loginId, loginPass;
    ProgressDialog pd;
    Session sessionManagement;
    LoginVM loginVM;
    LoginData logUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginVM = new ViewModelProvider(this).get(LoginVM.class);
        initView();


    }

    private void initView() {
        user_loginId_TIL = findViewById(R.id.user_loginId_TIL);
        user_pass_TIL = findViewById(R.id.user_pass_TIL);

        user_login_btn = findViewById(R.id.user_login_btn);
        user_login_btn.setOnClickListener(this);

        dManLoginId = findViewById(R.id.login_userMobileIET);
        dManLoginPass = findViewById(R.id.login_userPassIET);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login_btn:
                if (!validateEmail() | !validatePass()) {
                    user_login_btn.setEnabled(false);
                    return;
                } else {
                    //proceed to login
                    user_login_btn.setEnabled(true);
                    loginFun();

                }
                Intent in = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(in);
                finish();

                break;
        }
    }

    //Validate login input field
    private boolean validateEmail() {
        loginId = dManLoginId.getText().toString().trim();

        if (loginId.isEmpty()) {
            user_loginId_TIL.setError("Field can't be empty");
            return false;
        } else {
            user_loginId_TIL.setError(null);
            return true;
        }

    }

    private boolean validatePass() {
        loginPass = dManLoginPass.getText().toString().trim();
        if (loginPass.isEmpty()) {
            user_pass_TIL.setError("Password can't be empty");
            return false;
        } else {
            user_pass_TIL.setError(null);
            return true;
        }

    }

    private void loginFun() {
        sessionManagement = new Session(LoginActivity.this);
        HashMap<String, String> loginMap = new HashMap<>();
        //  loginMap.put("username",dManLoginId.getText().toString().trim());
        loginMap.put("username", loginId);
        loginMap.put("password", loginPass);
        // loginMap.put("password",dManLoginPass.getText().toString().trim());
        loginVM.LoginApiCall(loginMap);
        loginVM.getLoginUserDataObserver().observe(this, new Observer<LoginData>() {
            @Override
            public void onChanged(LoginData loginData) {
                logUserData = loginData;
                logUserData.getPhoto();

            }
        });

    }
}