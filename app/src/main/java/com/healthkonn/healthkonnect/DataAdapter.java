package com.healthkonn.healthkonnect;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.healthkonn.healthkonnect.model.HistoryDetails;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<HistoryDetails> historyDetails;
    private Context context;

    public DataAdapter(Context context,ArrayList<HistoryDetails> machineTypes) {
        this.context = context;
        this.historyDetails=machineTypes;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.date.setText(historyDetails.get(i).getDate());
        viewHolder.status.setText(historyDetails.get(i).getStatus());
        viewHolder.name.setText(historyDetails.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return historyDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView machinename;
        ImageView img_machine;
        TextView date,status,name;
        public ViewHolder(View view) {
            super(view);

            date = (TextView) view.findViewById(R.id.appt_date);
            status = (TextView) view.findViewById(R.id.status);
            name = (TextView) view.findViewById(R.id.pat_name);
        }
    }
}