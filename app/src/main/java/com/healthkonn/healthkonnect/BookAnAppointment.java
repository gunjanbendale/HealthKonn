package com.healthkonn.healthkonnect;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.healthkonn.healthkonnect.model.Result;
import com.healthkonn.healthkonnect.network.RetrofitInterface;
import com.healthkonn.healthkonnect.utils.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookAnAppointment extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Button submit;
    EditText patient,age,city,hospital,drname;
    EditText date;
    EditText time;
    Result result;

    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit=builder.build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_an_appointment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        patient = (EditText) findViewById(R.id.patient);
        age = (EditText) findViewById(R.id.age);
        city = (EditText) findViewById(R.id.city);
        hospital = (EditText) findViewById(R.id.hospital);
        drname = (EditText) findViewById(R.id.drname);

        date = (EditText) findViewById(R.id.apptdate);
        time = (EditText) findViewById(R.id.appttime);
        datePickerDialog = new DatePickerDialog(this);
        submit = (Button) findViewById(R.id.submit);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(BookAnAppointment.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY); // current hour
                int mMinute = c.get(Calendar.MINUTE); // current min
                // date picker dialog
                timePickerDialog = new TimePickerDialog(BookAnAppointment.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hour,
                                                  int minute) {
                                // set day of month , month and year value in the edit text
                                time.setText(hour + ":"+ minute );
                            }
                        }, mHour, mMinute,false);
                timePickerDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitreq();
            }
        });

    }

    private void submitreq(){
        String name_user = patient.getText().toString();
        String agep = age.getText().toString();
        String cityp = city.getText().toString();
        String hospitalp = hospital.getText().toString();
        String drnamep = drname.getText().toString();
        String datep = date.getText().toString();
        String timep = time.getText().toString();
        if(name_user.isEmpty()){
            patient.setError("Name is Required");
            patient.requestFocus();
        }
        if (agep.isEmpty()){
            age.setError("Age is required");
            age.requestFocus();
        }
        if (cityp.isEmpty()){
            city.setError("City require");
            city.requestFocus();
        }
        if (hospitalp.isEmpty()){
            hospital.setError("Hospital Name required");
            hospital.requestFocus();
        }
        if (drnamep.isEmpty()){
            drname.setError("Drname Is Required");
            drname.requestFocus();
        }
        if (datep.isEmpty()){
            date.setError("Date required");
            date.requestFocus();
        }
        if (timep.isEmpty()){
            time.setError("Time required");
            time.requestFocus();
        }
        submitdata(name_user,agep,hospitalp,drnamep,cityp,datep,timep);
    }

    private void submitdata(String n,String a,String h,String d,String c,String da,String ti){
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Map<String,String> map = new HashMap<>();
        map.put("patname",n);
        map.put("age",a);
        map.put("city",c);
        map.put("Hospital",h);
        map.put("docname",d);
        map.put("apptDate",da);
        map.put("apptTime",ti);
        map.put("dateCurr",Calendar.getInstance().getTime().toString());
        Call<Result> call=retrofitInterface.bookappt(map);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                result=response.body();
                Toast.makeText(BookAnAppointment.this,new Gson().toJson(result.getMessage()),Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(BookAnAppointment.this,Dashboard.class));
                Toast.makeText(BookAnAppointment.this,"Enter mob and password",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(BookAnAppointment.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
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
            startActivity(new Intent(BookAnAppointment.this,Dashboard.class));
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
