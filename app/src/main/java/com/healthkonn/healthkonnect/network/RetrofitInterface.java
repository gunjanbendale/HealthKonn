package com.healthkonn.healthkonnect.network;


import android.support.annotation.CallSuper;

import com.healthkonn.healthkonnect.model.Result;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("/login")
    Call<Result> login(@Field("username") String usrname, @Field("password") String password);

    @FormUrlEncoded
    @POST("/signup")
    Call<Result> signup(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/bookappt")
    Call<Result> bookappt(@FieldMap Map<String,String> map);


    @FormUrlEncoded
    @POST("/searchbank")
    Call<Result> searchbank(@FieldMap Map<String,String> map);

    @GET("/logout")
    Call<Result> logout();
}
