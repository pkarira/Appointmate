package com.example.thispc.appointmate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by this pc on 15-03-2016.
 */
public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.ListViewHolder> {

    List<HospitalDetails> hospitalList;
    Activity parentAct;

    public HospitalListAdapter(List<HospitalDetails> hospitalList, Activity activity){
        this.hospitalList = hospitalList;
        parentAct = activity;
    }

    @Override
    public HospitalListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.hospital_card_layout, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HospitalListAdapter.ListViewHolder holder, int position) {
        HospitalDetails hd = hospitalList.get(position);
        holder.vName.setText(hd.getName().toString());
        holder.vContact.setText(String.valueOf(hd.getContact()));
        holder.vRating.setText(String.valueOf(hd.getRating()));
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vName;
        protected TextView vContact;
        protected TextView vRating;
        protected ImageView vImg;

        public ListViewHolder(View vi) {
            super(vi);
            vName = (TextView) vi.findViewById(R.id.txt_name_c);
            vContact = (TextView) vi.findViewById(R.id.txt_contact);
            vRating = (TextView) vi.findViewById(R.id.txt_rating);
            vImg = (ImageView) vi.findViewById(R.id.img_c);
            vi.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(parentAct , DoctorList.class);
            intent.putExtra("hospital_name",vName.getText().toString());
            parentAct.startActivity(intent);
        }
    }

}
