package com.healthkonn.healthkonnect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthkonn.healthkonnect.model.BloodBankData;
import com.healthkonn.healthkonnect.model.BloodBankResultsData;
import com.healthkonn.healthkonnect.model.History;
import com.healthkonn.healthkonnect.model.HistoryDetails;

import java.util.ArrayList;

public class BDataAdapter  extends RecyclerView.Adapter<BDataAdapter.ViewHolder> {
    private BloodBankData historyDetails;
    private Context context;

    public BDataAdapter(Context context,BloodBankData history) {
        this.context = context;
        this.historyDetails=history;

    }

    @Override
    public BDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bloodbankres, viewGroup, false);
        return new BDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BDataAdapter.ViewHolder viewHolder, int i) {

        ArrayList<BloodBankResultsData> hist = (ArrayList<BloodBankResultsData>) historyDetails.getBloodbanks();
        viewHolder.name.setText(hist.get(i).getName());
        viewHolder.area.setText(hist.get(i).getArea());
        viewHolder.contact.setText(hist.get(i).getContact());
    }

    @Override
    public int getItemCount() {
        return historyDetails.getBloodbanks().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView area,contact,name;
        public ViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.bloodbankname);
            area = (TextView) view.findViewById(R.id.bloodbankarea);
            contact = (TextView) view.findViewById(R.id.contact);
        }
    }
}
