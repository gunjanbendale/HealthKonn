package com.healthkonn.healthkonnect.network;


import com.healthkonn.healthkonnect.model.BloodBankData;
import com.healthkonn.healthkonnect.model.History;
import com.healthkonn.healthkonnect.model.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @GET("/")
    Call<Result>session_manage(@Header("authorization")String authtoken);

    @FormUrlEncoded
    @POST("/login")
    Call<Result> login(@Field("username") String usrname, @Field("password") String password);

    @FormUrlEncoded
    @POST("/signup")
    Call<Result> signup(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/profile")
    Call<Result> profile(@Field("id") String id);

    @FormUrlEncoded
    @POST("/bookappt")
    Call<Result> bookappt(@FieldMap Map<String,String> map);



    @FormUrlEncoded
    @POST("/searchbank")
    Call<BloodBankData> searchbank(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/appthistory")
    Call<History> appthistory(@Field("id") String id);

    @GET("/logout")
    Call<Result> logout();
}
