package com.example.thispc.appointmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin1 extends AppCompatActivity {

    DataBaseHandler dbh;
    Button save;
    EditText et1;
    EditText et2;
    EditText et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin1);
        dbh = new DataBaseHandler(this);
        save = (Button) findViewById(R.id.save);
        et1 = (EditText) findViewById(R.id.name);
        et2 = (EditText) findViewById(R.id.username);
        et3 = (EditText) findViewById(R.id.pass);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String username = et2.getText().toString();
                    String pass = et3.getText().toString();
                    String name = et1.getText().toString();
                    UserDetails ud = new UserDetails(MainActivity.user_num, name, username, pass);
                    dbh.addUser(ud);
                    MainActivity.user_num++;
                    Toast.makeText(signin1.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
