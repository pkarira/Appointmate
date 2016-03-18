package com.example.thispc.appointmate;

/**
 * Created by Harjot on 16-Mar-16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsManager extends BroadcastReceiver {

    static String recievedMSG = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        Toast.makeText(context,"Recieved", Toast.LENGTH_SHORT).show();
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                Date date = new Date(msg.getTimestampMillis());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                if (msg.getOriginatingAddress().equals(MainActivity.serverNumber)) {
                    recievedMSG = msg.getDisplayMessageBody();
                    Toast.makeText(context,recievedMSG, Toast.LENGTH_SHORT).show();
                    BookAppointment.parent.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (recievedMSG.contains("Booking")) {
                                BookAppointment.progress2.dismiss();
                                BookAppointment.cnt1=0;
                                BookAppointment.cnt2=0;
                                BookAppointment.cnt3=0;
                            } else {
                                if(BookAppointment.progress!=null)
                                    BookAppointment.progress.dismiss();
                                BookAppointment.updateSlotStrings();
                                BookAppointment.btn1.setVisibility(View.VISIBLE);
                                BookAppointment.btn2.setVisibility(View.VISIBLE);
                                BookAppointment.btn3.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                }
            }
        }
    }
}