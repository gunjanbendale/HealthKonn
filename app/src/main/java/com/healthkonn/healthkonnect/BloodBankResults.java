package com.healthkonn.healthkonnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.healthkonn.healthkonnect.model.BloodBankData;
import com.healthkonn.healthkonnect.model.History;
import com.healthkonn.healthkonnect.model.Result;
import com.healthkonn.healthkonnect.network.RetrofitInterface;
import com.healthkonn.healthkonnect.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BloodBankResults extends AppCompatActivity {

    Intent intent;
    String bgrp,ct,area;
    Result result;
    BloodBankData bloodBankData;
    Gson gson = new GsonBuilder().setLenient().create();
    OkHttpClient client = new OkHttpClient();
    Retrofit.Builder builder=new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson));
    Retrofit retrofit=builder.build();
    RetrofitInterface retrofitInterface=retrofit.create(RetrofitInterface.class);
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    BDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_results);
        intent = getIntent();
        bgrp = intent.getStringExtra("bgrp");
        ct = intent.getStringExtra("ct");
        area = intent.getStringExtra("area");
        result = intent.getParcelableExtra("result");

        Map<String,String> map = new HashMap<>();
        map.put("bgrp",bgrp);
        map.put("city",ct);
        map.put("area",area);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<BloodBankData> call=retrofitInterface.searchbank(map);
        call.enqueue(new Callback<BloodBankData>() {
            @Override
            public void onResponse(Call<BloodBankData> call, Response<BloodBankData> response) {
                bloodBankData=response.body();
                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()));
                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()));
                adapter= new BDataAdapter(BloodBankResults.this,bloodBankData);
                recyclerView=(RecyclerView) findViewById(R.id.bloodbankresult);

                recyclerView.setLayoutManager(new GridLayoutManager(BloodBankResults.this,1));
                recyclerView.setAdapter(adapter);
                progressDialog.cancel();

            }

            @Override
            public void onFailure(Call<BloodBankData> call, Throwable t) {
                Toast.makeText(BloodBankResults.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                Intent intent1 =new Intent(this,Dashboard.class);
                intent1.putExtra("result",result);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            moveTaskToBack(true);
            Intent intent2 =new Intent(BloodBankResults.this,Dashboard.class);
            finish();
            startActivity(intent2);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Intent intent3= new Intent(this,Dashboard.class);
        finish();
        startActivity(intent3);
    }

}
