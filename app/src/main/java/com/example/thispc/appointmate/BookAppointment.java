package com.example.thispc.appointmate;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.telephony.SmsManager;

import java.util.ArrayList;
import java.util.List;

public class BookAppointment extends AppCompatActivity {

    /* Variables*/

    TextView name, type, qual, contact;
    static long bookingNumber;
    static int doctorId;
    static ProgressDialog progress;
    static ProgressDialog progress2;
    Button btn;
    static Activity parent;
    static Button btn1, btn2, btn3;
    static ListView slots;
    static int cnt1 = 0;
    static int cnt2 = 0;
    static int cnt3 = 0;
    static List<String> slotsInfo1;
    static List<String> slotsInfo2;
    static List<String> slotsInfo3;
    static String[] slotsInfo1Copy;
    static String[] slotsInfo2Copy;
    static String[] slotsInfo3Copy;
    static int buttonSelected = 1;

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
        btn1 = (Button) findViewById(R.id.today);
        btn2 = (Button) findViewById(R.id.tomorrow);
        btn3 = (Button) findViewById(R.id.dayAfterTmrw);
        btn2.setAlpha((float) 0.3);
        btn3.setAlpha((float) 0.3);
        slots = (ListView) findViewById(R.id.slotList);
        slotsInfo1 = new ArrayList<>();
        slotsInfo2 = new ArrayList<>();
        slotsInfo3 = new ArrayList<>();
        slotsInfo1Copy = new String[25];
        slotsInfo2Copy = new String[25];
        slotsInfo3Copy = new String[25];
        cnt1 = 0;
        cnt2 = 0;
        cnt3 = 0;
        name.setText(data.getString("name"));
        type.setText(data.getString("type"));
        qual.setText(data.getString("qual"));
        contact.setText(String.valueOf(data.getLong("contact")));
        bookingNumber = data.getLong("contact");
        doctorId = data.getInt("id");
        parent = this;
        btn1.setText(MainActivity.today);
        btn2.setText(MainActivity.tmrw);
        btn3.setText(MainActivity.dayAftertmrw);
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
                        String smsBody = String.valueOf("<>" + doctorId);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
                    }
                }).start();
                btn.setText("Refresh List/सूची ताज़ा करे");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelected = 1;
                btn1.setAlpha(1);
                btn2.setAlpha((float) 0.3);
                btn3.setAlpha((float) 0.3);
                updateList();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelected = 2;
                btn1.setAlpha((float) 0.3);
                btn2.setAlpha(1);
                btn3.setAlpha((float) 0.3);
                updateList();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelected = 3;
                btn1.setAlpha((float) 0.3);
                btn2.setAlpha((float) 0.3);
                btn3.setAlpha(1);
                updateList();
            }
        });

        slots.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (buttonSelected == 1) {
                    progress2 = ProgressDialog.show(BookAppointment.this, "Please Wait",
                            "Waiting for Server Message", true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // do the thing that takes a long time
                            String phoneNumber = MainActivity.serverNumber;
                            int tmp = Integer.parseInt(String.valueOf(slotsInfo1Copy[position])) % 100;
                            String smsBody = String.valueOf("><" + String.valueOf((tmp < 10) ? ("0" + tmp) : tmp) + doctorId);
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
                        }
                    }).start();
                } else if (buttonSelected == 2) {
                    progress2 = ProgressDialog.show(BookAppointment.this, "Please Wait",
                            "Waiting for Server Message", true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // do the thing that takes a long time
                            String phoneNumber = MainActivity.serverNumber;
                            int tmp = (Integer.parseInt(String.valueOf(slotsInfo2Copy[position])) + 24) % 100;
                            String smsBody = String.valueOf("><" + String.valueOf((tmp < 10) ? ("0" + tmp) : tmp) + doctorId);
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
                        }
                    }).start();
                } else {
                    progress2 = ProgressDialog.show(BookAppointment.this, "Please Wait",
                            "Waiting for Booking Confirmation", true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // do the thing that takes a long time
                            String phoneNumber = MainActivity.serverNumber;
                            int tmp = (Integer.parseInt(String.valueOf(slotsInfo3Copy[position])) + 48) % 100;
                            String smsBody = String.valueOf("><" + String.valueOf((tmp < 10) ? ("0" + tmp) : tmp) + doctorId);
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
                        }
                    }).start();
                }
            }
        });

    }

    public static void updateList() {
        if (buttonSelected == 1) {
            slots.setAdapter(new ArrayAdapter<String>(parent, R.layout.list_item, R.id.slot, slotsInfo1));
        } else if (buttonSelected == 2) {
            slots.setAdapter(new ArrayAdapter<String>(parent, R.layout.list_item, R.id.slot, slotsInfo2));
        } else {
            slots.setAdapter(new ArrayAdapter<String>(parent, R.layout.list_item, R.id.slot, slotsInfo3));
        }
    }

    public static void updateSlotStrings() {
        for (int i = 0; i < 24; i++) {
            if (com.example.thispc.appointmate.SmsManager.recievedMSG.charAt(i) == '0') {
                slotsInfo1.add(((i < 10) ? ("0" + i) : i) + "00hrs to " + (((i + 1) < 10) ? ("0" + (i + 1)) : (i + 1)) + "00hrs");
                slotsInfo1Copy[cnt1] = String.valueOf(i);
                cnt1++;
            }
        }
        for (int i = 24; i < 48; i++) {
            if (com.example.thispc.appointmate.SmsManager.recievedMSG.charAt(i) == '0') {
                slotsInfo2.add((((i - 24) < 10) ? ("0" + (i - 24)) : (i - 24)) + "00hrs to " + (((i - 23) < 10) ? ("0" + (i - 23)) : (i - 23)) + "00hrs");
                slotsInfo2Copy[cnt2] = String.valueOf(i - 24);
                cnt2++;
            }
        }
        for (int i = 48; i < 72; i++) {
            if (com.example.thispc.appointmate.SmsManager.recievedMSG.charAt(i) == '0') {
                slotsInfo3.add((((i - 48) < 10) ? ("0" + (i - 48)) : (i - 48)) + "00hrs to " + (((i - 47) < 10) ? ("0" + (i - 47)) : (i - 47)) + "00hrs");
                slotsInfo3Copy[cnt3] = String.valueOf(i - 48);
                cnt3++;
            }
        }
        updateList();
    }
}
