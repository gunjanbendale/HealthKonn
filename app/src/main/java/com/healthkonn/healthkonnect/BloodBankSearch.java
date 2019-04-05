package com.healthkonn.healthkonnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.healthkonn.healthkonnect.model.Result;
import com.healthkonn.healthkonnect.network.RetrofitInterface;
import com.healthkonn.healthkonnect.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BloodBankSearch extends AppCompatActivity {

    EditText bloodgrp,city,area;
    Result result;
    Intent intent;

    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit=builder.build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent=getIntent();
        bloodgrp = (EditText) findViewById(R.id.bloodgrp);
        city = (EditText) findViewById(R.id.city);
        area = (EditText) findViewById(R.id.area);
        result = intent.getParcelableExtra("result");
        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitreq();
            }
        });



    }
    private void submitreq(){
        String bgrp = bloodgrp.getText().toString();
        String ct = city.getText().toString();
        String ar = area.getText().toString();
        if(bgrp.isEmpty()){
            bloodgrp.setError("Name is Required");
            bloodgrp.requestFocus();
        }
        if(ct.isEmpty()){
            city.setError("Name is Required");
            city.requestFocus();
        }
        if(ar.isEmpty()){
            area.setError("Name is Required");
            area.requestFocus();
        }
        submitdata(bgrp,ct,ar);

    }

    private void submitdata(String bgrp,String ct,String area){
       Intent intent = new Intent(BloodBankSearch.this,BloodBankResults.class);
       intent.putExtra("bgrp",bgrp);
       intent.putExtra("ct",ct);
       intent.putExtra("area",area);
       intent.putExtra("result",result);
       finish();
       startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                startActivity(new Intent(this,Dashboard.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            moveTaskToBack(true);
            finish();
            startActivity(new Intent(BloodBankSearch.this,Dashboard.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,Dashboard.class));
    }
}
