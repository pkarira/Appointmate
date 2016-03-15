package com.example.thispc.appointmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class HospitalList extends AppCompatActivity {

    RecyclerView recList;
    DataBaseHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        recList = (RecyclerView) findViewById(R.id.hospitalList_recycler);
        dbh = new DataBaseHandler(this);
        addToList();
    }

    private List<HospitalDetails> createList() {
        List<HospitalDetails> result;
        result=dbh.getAllHospitals();
        return result;
    }
    public void addToList(){
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        HospitalListAdapter ca = new HospitalListAdapter(createList(),this);
        recList.setAdapter(ca);
    }

}
