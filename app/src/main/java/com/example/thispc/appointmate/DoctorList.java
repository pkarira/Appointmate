package com.example.thispc.appointmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DoctorList extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    DataBaseHandler dbh;
    RecyclerView recList;
    String hospital_name;
    TextView hName;
    Spinner type_spinner;
    String typeSelected = "all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        hName = (TextView) findViewById(R.id.hospital_name);
        dbh = new DataBaseHandler(this);
        recList = (RecyclerView) findViewById(R.id.doctorlist_recycler);
        type_spinner = (Spinner) findViewById(R.id.type_spinner);
        Bundle data = getIntent().getExtras();
        hospital_name = data.getString("hospital_name");
        hName.setText(hospital_name);
        addToList();

        type_spinner.setOnItemSelectedListener(this);

        List<String> types = new ArrayList<>();
        types.add("All/सब");
        types.add("Dentist/दंत चिकित्सक");
        types.add("Dermatologist/त्वचा विशेषज्ञ");
        types.add("Cardiologist/हृदय रोग विशेषजञ");
        types.add("Gynecologist/प्रसूतिशास्री");
        types.add("Neurologist/न्यूरोलॉजिस्");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,types);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(dataAdapter);
    }

    private List<DoctorDetails> createList() {
        List<DoctorDetails> result;
        result=(typeSelected.equals("all"))?dbh.getAllDoctors():dbh.getAllDoctors(typeSelected);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        typeSelected = parent.getItemAtPosition(position).toString();
        addToList();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
