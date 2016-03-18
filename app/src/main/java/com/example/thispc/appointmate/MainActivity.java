package com.example.thispc.appointmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DataBaseHandler dbh;
    static int user_num = 1;
    SharedPreferences prefs;
    final static String serverNumber = "+917248187747";
    static String today, tmrw, dayAftertmrw;

    public void login(View v) {
        Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
        try {
            EditText et1 = (EditText) findViewById(R.id.editText);
            EditText et2 = (EditText) findViewById(R.id.editText2);
            String username = et1.getText().toString();
            String pass = et2.getText().toString();
            UserDetails ud = dbh.getuser(username);
            if (ud != null) {

                if (pass.equals(ud.getPassword())) {
                    Toast t = Toast.makeText(getApplicationContext(), "correct Password", Toast.LENGTH_SHORT);
                    t.show();
                    Intent ic = new Intent(MainActivity.this, HospitalList.class);
                    startActivity(ic);
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void signin(View v) {
        Intent ic = new Intent(MainActivity.this, signin1.class);
        startActivity(ic);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbh = new DataBaseHandler(this);
        prefs = getSharedPreferences("ABCDE", MODE_PRIVATE);
        user_num = prefs.getInt("UserNum", user_num);

        Calendar c = Calendar.getInstance();

        today = String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(c.get(Calendar.MONTH)) + "/" + String.valueOf(c.get(Calendar.YEAR));
        tmrw = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.MONTH)) + "/" + String.valueOf(c.get(Calendar.YEAR));
        dayAftertmrw = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 2) + "/" + String.valueOf(c.get(Calendar.MONTH)) + "/" + String.valueOf(c.get(Calendar.YEAR));

        dbh.clear();
        HospitalDetails hd = new HospitalDetails(1,"Hospital ONE","#1234,ABC Road, Rampur",1234567890,3);
        dbh.addHospital(hd);
        hd = new HospitalDetails(2, "Hospital Two", "#1234,ABC Road, Roorkee", 2134567890, 5);
        dbh.addHospital(hd);

        DoctorDetails dd = new DoctorDetails(1, "Dr. Saxena", Long.parseLong(serverNumber), "M.B.B.S.", "Dentist");
        dbh.addDoctor(dd);
        dd = new DoctorDetails(2, "Dr. Mehta", Long.parseLong(serverNumber), "M.B.B.S.", "Dermatologist");
        dbh.addDoctor(dd);
        dd = new DoctorDetails(3, "Dr. Sharma", Long.parseLong(serverNumber), "M.B.B.S.", "Cardiologist");
        dbh.addDoctor(dd);
        dd = new DoctorDetails(4, "Dr. Gupta", Long.parseLong(serverNumber), "M.B.B.S.", "Gynecologist");
        dbh.addDoctor(dd);
        dd = new DoctorDetails(5, "Dr. R. Babbar", Long.parseLong(serverNumber), "M.B.B.S.", "Neurologist");
        dbh.addDoctor(dd);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("UserNum", user_num);
        editor.commit();
    }
}
