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
       String s=String.valueOf(hd.getRating());
        if(s.equals("1"))
        {
            holder.vImg1.setImageResource(R.drawable.star);
        }
        if(s.equals("2"))
        {
            holder.vImg1.setImageResource(R.drawable.star);
            holder.vImg2.setImageResource(R.drawable.star);
        }
        if(s.equals("3"))
        {
            holder.vImg1.setImageResource(R.drawable.star);
            holder.vImg2.setImageResource(R.drawable.star);
            holder.vImg3.setImageResource(R.drawable.star);
        }
        if(s.equals("4"))
        {
            holder.vImg1.setImageResource(R.drawable.star);
            holder.vImg2.setImageResource(R.drawable.star);
            holder.vImg3.setImageResource(R.drawable.star);
            holder.vImg4.setImageResource(R.drawable.star);
        }
        if(s.equals("5"))
        {
            holder.vImg1.setImageResource(R.drawable.star);
            holder.vImg2.setImageResource(R.drawable.star);
            holder.vImg3.setImageResource(R.drawable.star);
            holder.vImg4.setImageResource(R.drawable.star);
            holder.vImg5.setImageResource(R.drawable.star);
        }
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
        protected ImageView vImg1;
        protected ImageView vImg2;
        protected ImageView vImg3;
        protected ImageView vImg4;
        protected ImageView vImg5;

        public ListViewHolder(View vi) {
            super(vi);
            vName = (TextView) vi.findViewById(R.id.txt_name_c);
            vContact = (TextView) vi.findViewById(R.id.txt_contact);

            vImg = (ImageView) vi.findViewById(R.id.img_c);
            vImg1 = (ImageView) vi.findViewById(R.id.image1);
            vImg2 = (ImageView) vi.findViewById(R.id.image2);
            vImg3 = (ImageView) vi.findViewById(R.id.image3);
            vImg4 = (ImageView) vi.findViewById(R.id.image4);
            vImg5 = (ImageView) vi.findViewById(R.id.image5);

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
