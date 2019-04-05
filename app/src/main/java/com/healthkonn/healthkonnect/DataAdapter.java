package com.healthkonn.healthkonnect;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.healthkonn.healthkonnect.model.History;
import com.healthkonn.healthkonnect.model.HistoryDetails;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private History historyDetails;
    private Context context;

    public DataAdapter(Context context,History history) {
        this.context = context;
        this.historyDetails=history;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        ArrayList<HistoryDetails> hist = (ArrayList<HistoryDetails>) historyDetails.getOrders();
        viewHolder.date.setText(hist.get(i).getDate());
        viewHolder.status.setText(hist.get(i).getStatus());
        viewHolder.name.setText(hist.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return historyDetails.getOrders().size();
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