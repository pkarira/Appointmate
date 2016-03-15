package com.example.thispc.appointmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class DoctorList extends AppCompatActivity {

    DataBaseHandler dbh;
    RecyclerView recList;
    String hospital_name;
    TextView hName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        hName = (TextView) findViewById(R.id.hospital_name);
        dbh = new DataBaseHandler(this);
        recList = (RecyclerView) findViewById(R.id.doctorlist_recycler);
        Bundle data = getIntent().getExtras();
        hospital_name = data.getString("hospital_name");
        hName.setText(hospital_name);
        addToList();
    }

    private List<DoctorDetails> createList() {
        List<DoctorDetails> result;
        result=dbh.getAllDoctors();
        return result;
    }
    public void addToList(){
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        DoctorListAdapter ca = new DoctorListAdapter(createList(),this);
        recList.setAdapter(ca);
    }

}
