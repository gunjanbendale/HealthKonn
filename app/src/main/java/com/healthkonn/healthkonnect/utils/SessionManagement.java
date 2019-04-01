package com.healthkonn.healthkonnect.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.healthkonn.healthkonnect.LoginActivity;
import com.healthkonn.healthkonnect.model.Result;
import com.healthkonn.healthkonnect.network.RetrofitInterface;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.internal.DiskLruCache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SessionManagement {
    //Shared Preference for session
    SharedPreferences sharedPreferences;

    // Editor for shared preferences
    SharedPreferences.Editor editor;

    //Context
    Context context;

    int PRIVATE_MODE=0;
    Gson gson = new GsonBuilder().setLenient().create();

    OkHttpClient client = new OkHttpClient();
    Retrofit.Builder builder=new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson));
    Retrofit retrofit=builder.build();
    RetrofitInterface retrofitInterface=retrofit.create(RetrofitInterface.class);


    public static final String PREF_NAME = "Login";

    public static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_MOB = "mobile";

    public static final String KEY_PASS = "password";

    public static final String KEY_ID = "id";

    public SessionManagement(Context context){
        this.context= context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();

    }

    public void createLoginSession(String mobile, String password,String id){
        editor.putBoolean(IS_LOGIN,true);

        editor.putString(KEY_MOB,mobile);

        editor.putString(KEY_PASS,password);

        editor.putString(KEY_ID,id);
        editor.commit();

    }

    public void checkLogin(Activity activity){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(context, LoginActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
            activity.finish();

        }
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_ID,sharedPreferences.getString(KEY_ID,null));

        user.put(KEY_MOB, sharedPreferences.getString(KEY_MOB, null));

        user.put(KEY_PASS, sharedPreferences.getString(KEY_PASS, null));
        // return user
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Call<Result> call= (Call<Result>) retrofitInterface.logout();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Intent i = new Intent(context, LoginActivity.class);
                i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(i);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
}
