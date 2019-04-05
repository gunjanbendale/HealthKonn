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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.healthkonn.healthkonnect.model.History;
import com.healthkonn.healthkonnect.model.HistoryDetails;
import com.healthkonn.healthkonnect.model.Result;
import com.healthkonn.healthkonnect.network.RetrofitInterface;
import com.healthkonn.healthkonnect.utils.Constants;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicalHistory extends AppCompatActivity {

    Intent intent;
    Result result;
    RecyclerView recyclerView;
    DataAdapter adapter;
    Gson gson = new GsonBuilder().setLenient().create();
    OkHttpClient client = new OkHttpClient();
    Retrofit.Builder builder=new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson));
    Retrofit retrofit=builder.build();
    RetrofitInterface retrofitInterface=retrofit.create(RetrofitInterface.class);
    ProgressDialog progressDialog;



    private History historyDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        intent=getIntent();
        result = intent.getParcelableExtra("result");
        String _id = result.getId();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<History> call=retrofitInterface.appthistory(_id);
        call.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                historyDetails=response.body();
                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()));
                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()));
                adapter= new DataAdapter(MedicalHistory.this,historyDetails);
                recyclerView=(RecyclerView) findViewById(R.id.apptHist);

                recyclerView.setLayoutManager(new GridLayoutManager(MedicalHistory.this,1));
                recyclerView.setAdapter(adapter);
                progressDialog.cancel();

            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                Toast.makeText(MedicalHistory.this,t.getMessage(),Toast.LENGTH_SHORT).show();
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
            Intent intent2 =new Intent(MedicalHistory.this,Dashboard.class);
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
