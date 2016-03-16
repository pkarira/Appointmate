package com.example.thispc.appointmate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.telephony.SmsManager;

public class BookAppointment extends AppCompatActivity {

    TextView name, type, qual, contact;
    static long bookingNumber;
    static int doctorId;
    static ProgressDialog progress;
    Button btn;
    static Activity parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        Bundle data = getIntent().getExtras();
        name = (TextView) findViewById(R.id.doctor_name);
        type = (TextView) findViewById(R.id.doctor_type);
        qual = (TextView) findViewById(R.id.doctor_qual);
        contact = (TextView) findViewById(R.id.doctor_contact);
        btn = (Button) findViewById(R.id.sendMSG);
        name.setText(data.getString("name"));
        type.setText(data.getString("type"));
        qual.setText(data.getString("qual"));
        contact.setText(String.valueOf(data.getLong("contact")));
        bookingNumber = data.getLong("contact");
        doctorId = data.getInt("id");
        parent = this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = ProgressDialog.show(BookAppointment.this, "Please Wait",
                        "Waiting for Server Message", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // do the thing that takes a long time
                        String phoneNumber = MainActivity.serverNumber;
                        String smsBody = String.valueOf("<>"+doctorId);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
                    }
                }).start();
            }
        });
    }
}
