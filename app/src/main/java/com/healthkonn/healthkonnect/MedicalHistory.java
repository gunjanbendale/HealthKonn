package com.healthkonn.healthkonnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.healthkonn.healthkonnect.model.HistoryDetails;
import com.healthkonn.healthkonnect.model.Result;

import java.util.ArrayList;

public class MedicalHistory extends AppCompatActivity {

    Intent intent;
    Result result;
    RecyclerView recyclerView;
    DataAdapter adapter;
    private ArrayList<HistoryDetails> historyDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        intent=getIntent();
        result = intent.getParcelableExtra("result");

        adapter= new DataAdapter(getApplicationContext(),historyDetails);
        recyclerView=(RecyclerView) findViewById(R.id.apptHist);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
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


    public ArrayList<HistoryDetails> filldatas(String S){
        ArrayList<HistoryDetails> data = new ArrayList<>();
       // String s=S.toLowerCase();
       // int sa=getResources().getIdentifier(s,"array",getPackageName());
       // String[] array=getResources().getStringArray(sa);

       // int i=0;
        //while(i<array.length){
         //   String x=S.toLowerCase()+i;
           // String r="https://drive.google.com/open?id=1CbhQ7PHAh5DBl_iQ394uMmFbO8dpjGsP/" + S.toLowerCase() + i + ".png";
           // data.add(new HistoryDetails(array[i],r));
          //  i++;
       // }
        return data;
    }



}
