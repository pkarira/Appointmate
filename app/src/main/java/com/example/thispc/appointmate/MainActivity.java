package com.example.thispc.appointmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHandler dbh;
    static int user_num = 1;
    SharedPreferences prefs;
    final static String serverNumber = "+917060906315";

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

        dbh.clear();

        HospitalDetails hd = new HospitalDetails(1,"Hospital ONE","#1234,ABC Road, LodaPur",1234567890,5);
        dbh.addHospital(hd);
        hd = new HospitalDetails(2,"Hospital Two","#1234,ABC Road, fsppur",2134567890,5);
        dbh.addHospital(hd);

        DoctorDetails dd = new DoctorDetails(1,"Dr. Saxena",123456789,"M.B.B.S.","type1");
        dbh.addDoctor(dd);
        dd = new DoctorDetails(2,"Dr. Mehta",123451212,"M.B.B.S.","type2");
        dbh.addDoctor(dd);
        dd = new DoctorDetails(3,"Dr. Sharma",123226789,"M.B.B.S.","type3");
        dbh.addDoctor(dd);
        dd = new DoctorDetails(4,"Dr. Gupta",333456789,"M.B.B.S.","type4");
        dbh.addDoctor(dd);
        dd = new DoctorDetails(5,"Dr. R. Babbar",Long.parseLong(serverNumber),"M.B.B.S.","type5");
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
