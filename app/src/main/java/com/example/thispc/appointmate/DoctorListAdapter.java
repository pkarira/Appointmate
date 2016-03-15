package com.example.thispc.appointmate;

import android.app.Activity;
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
public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ListViewHolder> {

    List<DoctorDetails> doctorList;
    Activity parentAct;

    public DoctorListAdapter(List<DoctorDetails> doctorList, Activity activity){
        this.doctorList = doctorList;
        parentAct = activity;
    }

    @Override
    public DoctorListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.hospital_card_layout, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.ListViewHolder holder, int position) {
        DoctorDetails dd = doctorList.get(position);
        holder.vName.setText(dd.getName().toString());
        holder.vContact.setText(String.valueOf(dd.getContact()));
        holder.vQual.setText(String.valueOf(dd.getQualification()));
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vName;
        protected TextView vContact;
        protected TextView vQual;
        protected ImageView vImg;

        public ListViewHolder(View vi) {
            super(vi);
            vName = (TextView) vi.findViewById(R.id.txt_name_c_doc);
            vContact = (TextView) vi.findViewById(R.id.txt_contact_doc);
            vQual = (TextView) vi.findViewById(R.id.txt_qual_doc);
            vImg = (ImageView) vi.findViewById(R.id.img_c_doc);
            vi.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
