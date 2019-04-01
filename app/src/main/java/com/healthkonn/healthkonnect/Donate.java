package com.healthkonn.healthkonnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Donate extends AppCompatActivity {
    EditText name,bloodgrp,lastdon,healthcond,age,weight,height,city,area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (EditText) findViewById(R.id.name);
        bloodgrp = (EditText) findViewById(R.id.bloodgrp);
        lastdon = (EditText) findViewById(R.id.lastdon);
        healthcond = (EditText) findViewById(R.id.healthcond);
        age = (EditText) findViewById(R.id.age);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        city = (EditText) findViewById(R.id.city);
        area = (EditText) findViewById(R.id.area);

        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitreq();
            }
        });

    }

    private void submitreq(){

    }

    private void submitdata(){

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
            startActivity(new Intent(Donate.this,Dashboard.class));
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
