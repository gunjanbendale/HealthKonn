package com.healthkonn.healthkonnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView na,ci,db,add,em,ph;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        intent = getIntent();
        String name =intent.getStringExtra("name");
        String city=intent.getStringExtra("city");
        String dob =intent.getStringExtra("dob");
        String address = intent.getStringExtra("address");
        String email = intent.getStringExtra("email");
        String mob = intent.getStringExtra("contact");
        na =(TextView) findViewById(R.id.name);
        ci =(TextView) findViewById(R.id.city);
        db =(TextView) findViewById(R.id.dob);
        add =(TextView) findViewById(R.id.address);
        em =(TextView) findViewById(R.id.email);
        ph =(TextView) findViewById(R.id.phn);
        na.append(name);
        ci.append(city);
        db.append(dob);
        add.append(address);
        em.append(email);
        ph.append(mob);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                Intent intent1 =new Intent(this,Dashboard.class);

                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            moveTaskToBack(true);
            finish();
            startActivity(new Intent(Profile.this,Dashboard.class));
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
