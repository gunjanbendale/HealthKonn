package com.healthkonn.healthkonnect;

import com.healthkonn.healthkonnect.utils.Constants;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.healthkonn.healthkonnect.model.LoginDetails;
import com.healthkonn.healthkonnect.model.Result;
import com.healthkonn.healthkonnect.network.RetrofitInterface;
import com.healthkonn.healthkonnect.utils.SessionManagement;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText mobile;
    private EditText password;
    private ProgressDialog progressDialog;
    private TextView signUp;
    private TextView forgotpass;
    SessionManagement sessionManagement;
    String token;
    public static final String TAG = LoginActivity.class.getSimpleName();
    private String mob,pass,id;
    Result result;
    Context context;
    Gson gson = new GsonBuilder().setLenient().create();

    OkHttpClient client = new OkHttpClient();
    Retrofit.Builder builder=new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson));
    Retrofit retrofit=builder.build();
    RetrofitInterface retrofitInterface=retrofit.create(RetrofitInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile= (EditText) findViewById(R.id.mobile);
        password=(EditText) findViewById(R.id.password);
        progressDialog = new ProgressDialog(LoginActivity.this);
        sessionManagement= new SessionManagement(getApplicationContext());
        forgotpass = (TextView) findViewById(R.id.for_Pass);
        signUp = (TextView) findViewById(R.id.SignUp);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        context = this.getApplicationContext();
    }
    public void SignUp(View view){

    }
    public void SignIN(View view){
        mob=mobile.getText().toString();
        pass=password.getText().toString();
        progressDialog.setMessage("Signing In");
        //startActivity(new Intent(this, DashboardActivity.class));
        startSignin();
    }
    public void startSignin() {

        String mob = mobile.getText().toString();
        String pass = password.getText().toString();

        if (mob.isEmpty()) {
            mobile.setError("Phone is Required");
            mobile.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }
        if (password.length() < 1) {
            password.setError("Minimum length of Password should be 2 characters");
            password.requestFocus();
            return;
        }
        loginprocess();
        progressDialog.show();
    }
    private void loginprocess()
    {
        //Login login=new Login(email,password);
        LoginDetails loginDetails=new LoginDetails(mob,pass);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", mob);
            jsonObject.put("password", pass);
            Log.e("Json:",jsonObject.toString());
            Call<Result> call = retrofitInterface.login(mob,pass);

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {

                    result = response.body(); //    have your all data
                    if (result.getMessage().equals("Oops! Wrong password")) {
                        progressDialog.cancel();
                        Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                        password.setError("Incorrect Password");
                        password.requestFocus();
                        return;
                    } else if (result.getMessage().equals("No user found")) {
                        progressDialog.cancel();
                        Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                        mobile.setError("Invalid User");
                        mobile.requestFocus();
                        return;
                    } else if (result.getMessage().equals("WELCOME PLEASE LOGIN")) {
                        progressDialog.cancel();
                        Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                    } else {
                        String msg = result.getMessage();
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                        Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                        Log.e("TAG", "response 33: " + response.body());
                        sessionManagement.createLoginSession(mob, pass, result.getToken());
                        Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("result", result);
                        finish();
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.cancel();
                }


            });
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
    Result getResult()
    {
        return result;
    }
}
