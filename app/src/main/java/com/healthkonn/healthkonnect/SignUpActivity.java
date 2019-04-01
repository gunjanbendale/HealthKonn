package com.healthkonn.healthkonnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class SignUpActivity extends AppCompatActivity {

    EditText name, password,mobile,address,email;
    Button signup;
    TextView already;
    Result result;
    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit=builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.fullName);
        password = (EditText) findViewById(R.id.password);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        signup = (Button) findViewById(R.id.SignUp);
        already = (TextView) findViewById(R.id.AHAA);
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startsignup();
            }
        });

    }
    private void startsignup(){
        String name_user = name.getText().toString();
        String pass = password.getText().toString();
        String mob = mobile.getText().toString();
        String mail = email.getText().toString();
        String add = address.getText().toString();
        if(name_user.isEmpty()){
            name.setError("Name is Required");
            name.requestFocus();
        }
        if (pass.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
        }
        if (mob.isEmpty()||mob.length()<10){
            mobile.setError("Mobile field must be 10 digits ");
            mobile.requestFocus();
        }
        if ((!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())){
            email.setError("Please enter a valid email address");
            email.requestFocus();
        }
        if (add.isEmpty()){
            address.setError("Address Is Required");
            address.requestFocus();
        }
        signupprocess(name_user,pass,mob,mail,add);

    }
    void signupprocess(String name, String pass,String mob,String email,String Address){
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",pass);
        map.put("mobile",mob);
        map.put("email",email);
        map.put("address",Address);
        Call<Result> call=retrofitInterface.signup(map);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                result=response.body();
                Toast.makeText(SignUpActivity.this,new Gson().toJson(result.getMessage()),Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                Toast.makeText(SignUpActivity.this,"Enter mob and password",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(SignUpActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
