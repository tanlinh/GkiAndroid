package com.example.gkiandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView txtmsg,txtmsg2;
    Button btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtmsg2 = (TextView) findViewById(R.id.txtmsg2);
        txtmsg = (TextView) findViewById(R.id.txtmsg);
        btnthoat = (Button) findViewById(R.id.btnThoat);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent i=getIntent();
        txtmsg.setText("manv : "+i.getStringExtra("manv"));
        txtmsg2.setText("tennv: "+i.getStringExtra("tennv"));
    }
    
}
