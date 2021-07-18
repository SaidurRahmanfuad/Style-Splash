package com.saidur.stylesplash.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.saidur.stylesplash.MainActivity;
import com.saidur.stylesplash.ui.login.LoginActivity;

import java.util.HashMap;

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int Private_Mode=0;   //file mode
    public static String IS_Login="isLogin";
    public static final String Pref_Name="Pref_Name"; //file name
    public static final String UID="uid";
    public static final String Empid="empid";
    public static final String Cmpid="compid";
    public static final String CmpName="compname";
    public static final String UserName="name";
    public static final String UserMobile="mobile";
    public static final String UserEmail="email";
    public static final String UserRole="userrole";

    public Session(Context context) {
        sharedPreferences = context.getSharedPreferences(Pref_Name,Private_Mode);
        this.context = context;
        editor=sharedPreferences.edit();
    }

    public void saveSessaion(String uid,String empid,String Compid,String Compname,String userName,String uMobile,String email,String urole){
        editor.putBoolean(IS_Login,true);
        editor.putString(UID,uid);
        editor.putString(Empid,empid);
        // editor.putString(LoginId,loginid);
        editor.putString(Cmpid,Compid);
        editor.putString(CmpName,Compname);
        editor.putString(UserName,userName);
        editor.putString(UserMobile,uMobile);
        editor.putString(UserEmail,email);
        editor.putString(UserRole,urole);
        editor.commit();
    }


    public HashMap<String,String> getUserInfo()
    {
        HashMap<String,String> UserData=new HashMap<>();
        UserData.put(UID,sharedPreferences.getString(UID,null));
        UserData.put(UserName,sharedPreferences.getString(UserName,null));
        UserData.put(UserMobile,sharedPreferences.getString(UserMobile,null));
        UserData.put(Empid,sharedPreferences.getString(Empid,null));
        UserData.put(UserRole,sharedPreferences.getString(UserRole,null));
        return UserData;
    }

    public void checkLogin(){
        if(this.isLoginFun())
        {
            Intent intent=new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

        }
        else {
            Intent intents = new Intent(context, LoginActivity.class);
            intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intents);


        }

    }
    private boolean isLoginFun() {
        return sharedPreferences.getBoolean(IS_Login,false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent=new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
