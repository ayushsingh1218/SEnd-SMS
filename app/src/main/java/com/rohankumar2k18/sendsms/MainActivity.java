package com.rohankumar2k18.sendsms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    EditText e1, e2;
    Button b1;
    private final static int SEND_SMS_PERMISSION_REQ=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        b1=findViewById(R.id.button);
        b1.setEnabled(false);


        if(checkPermission())
        {
            b1.setEnabled(true);
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                if(!TextUtils.isEmpty(s1)&&!TextUtils.isEmpty(s2))
                {

                    if(checkPermission())
                    {
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(s1,null,s2,null,null);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPermission() {

        int checkpermission= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        return checkpermission== PackageManager.PERMISSION_GRANTED;
    }
}
