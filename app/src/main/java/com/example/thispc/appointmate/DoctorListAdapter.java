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
                inflate(R.layout.doctor_detail_card, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.ListViewHolder holder, int position) {
        DoctorDetails dd = doctorList.get(position);
        holder.vName.setText(dd.getName().toString());
        holder.vType.setText(String.valueOf(dd.getType()));
        holder.vQual.setText(String.valueOf(dd.getQualification()));
        holder.vContact = dd.getContact();
        holder.vDoctorID = dd.getId();
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vName;
        protected TextView vType;
        protected TextView vQual;
        protected ImageView vImg;
        protected Long vContact;
        protected int vDoctorID;

        public ListViewHolder(View vi) {
            super(vi);
            vName = (TextView) vi.findViewById(R.id.txt_name_c_doc);
            vType = (TextView) vi.findViewById(R.id.txt_type_doc);
            vQual = (TextView) vi.findViewById(R.id.txt_qual_doc);
            vImg = (ImageView) vi.findViewById(R.id.img_c_doc);
            vi.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(parentAct,BookAppointment.class);
            intent.putExtra("name",vName.getText());
            intent.putExtra("type",vType.getText());
            intent.putExtra("qual",vQual.getText());
            intent.putExtra("contact",vContact);
            intent.putExtra("id",vDoctorID);
            parentAct.startActivity(intent);
        }
    }

}
