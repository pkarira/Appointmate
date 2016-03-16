package com.example.thispc.appointmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BookAppointment extends AppCompatActivity {

    TextView name,type,qual,contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        Bundle data = getIntent().getExtras();
        name= (TextView) findViewById(R.id.doctor_name);
        type= (TextView) findViewById(R.id.doctor_type);
        qual= (TextView) findViewById(R.id.doctor_qual);
        contact= (TextView) findViewById(R.id.doctor_contact);
        name.setText(data.getString("name"));
        type.setText(data.getString("type"));
        qual.setText(data.getString("qual"));
        contact.setText(String.valueOf(data.getLong("contact")));
    }
}
