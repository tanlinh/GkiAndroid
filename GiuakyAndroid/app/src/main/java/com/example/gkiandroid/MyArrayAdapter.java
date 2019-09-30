package com.example.gkiandroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<NhanVien> {

    Activity context = null;
    ArrayList<NhanVien> myarr = null;
    int layoutid;
    public MyArrayAdapter( Activity context,  int layoutid,  ArrayList<NhanVien> object) {
        super(context,layoutid,object);
        this.context = context;
        this.layoutid = layoutid;
        this.myarr = object;
    }

    //nhấn vào textview sẽ hiện lên
    /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText("position :"+i+" ; value ="+i);

            }
        });
      */

    //hiện nhân viên lên trên màn hình khi nhấn vào listview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =context.getLayoutInflater();
        convertView = layoutInflater.inflate(layoutid,null);
        final TextView textView = (TextView) convertView.findViewById(R.id.txtitem);
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        final NhanVien nv = myarr.get(position);
        textView.setText(nv.toString());
        if(nv.getGioitinh()=="nam"){
            imageView.setImageResource(R.drawable.iconnam);
        }
        else{
            imageView.setImageResource(R.drawable.iconnu);
        }
            return convertView;
    }
}
