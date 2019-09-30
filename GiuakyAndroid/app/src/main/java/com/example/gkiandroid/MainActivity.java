package com.example.gkiandroid;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //tập tin lưu trạng thái
    String prefname = "my_data";

    CheckBox checkBox;
    EditText edtmanv, edttennv;
    Button btnnhap,btnxoa,btnluuthongtin;
    ListView lstview = null;
    ArrayList<NhanVien> arrnv = null;
    MyArrayAdapter adapter = null;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox =(CheckBox) findViewById(R.id.chksaveacount);

        edtmanv = (EditText) findViewById(R.id.edtmaphongban);
        edttennv = (EditText) findViewById(R.id.edttenphongban);
        lstview = (ListView) findViewById(R.id.listview);
        arrnv = new ArrayList<NhanVien>();
        //khởi tạo đối tượng adapter và gán data
        adapter =  new MyArrayAdapter(this,R.layout.my_item_layout,arrnv);
        lstview.setAdapter(adapter);
        btnxoa = (Button) findViewById(R.id.btnxoa);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        btnnhap = (Button) findViewById(R.id.btnluu);

        //lưu trên đối tượng shared-preferences
        btnluuthongtin = (Button) findViewById(R.id.btnluuthongtin);
        btnluuthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luuthongtin();
            }
        });

        btnnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        nhap();
            }
        });

          btnxoa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                xoa();
        }
        });
    }
    public void nhap(){
        String ma = edtmanv.getText().toString();
        String ten = edttennv.getText().toString();
        String gioitinh = "nam";
        if(radioGroup.getCheckedRadioButtonId() == R.id.radnu){
            gioitinh = "nu";
        }
        NhanVien nv = new NhanVien();
        nv.setMannv(ma);
        nv.setHoten(ten);
        nv.setGioitinh(gioitinh);
        arrnv.add(nv);
        //hàm cập nhật giao diện
        adapter.notifyDataSetChanged();
        edtmanv.setText("");
        edttennv.setText("");
        edtmanv.requestFocus();
    }
    public void xoa(){
        for(int i=lstview.getChildCount()-1;i>=0;i--)
        {
            //lấy ra dòng thứ i trong ListView
            //Dòng thứ i sẽ có 3 phần tử: ImageView, TextView, Checkbox
            View v=lstview.getChildAt(i);
            //Ta chỉ lấy CheckBox ra kiểm tra
            CheckBox chk=(CheckBox) v.findViewById(R.id.chkitem);
            //Nếu nó Checked thì xóa ra khỏi arrEmployee
            if(chk.isChecked())
            {
                //xóa phần tử thứ i ra khỏi danh sách
                arrnv.remove(i);
            }
        }
        //Sau khi xóa xong thì gọi update giao diện
        adapter.notifyDataSetChanged();
    }
    public void luuthongtin(){
        finish();//đóng màn hình hiện tại
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("manv", edtmanv.getText().toString());
        intent.putExtra("tennv", edttennv.getText().toString());
        startActivity(intent);//mở màn hình mới
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //gọi hàm lưu trạng thái ở đây
        savingPreferences();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //gọi hàm đọc trạng thái ở đây
        restoringPreferences();
    }
    /**
     * hàm lưu trạng thái
     */
    public void savingPreferences()
    {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre=getSharedPreferences
                (prefname, MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor=pre.edit();
        String manv=edtmanv.getText().toString();
        String tennv=edttennv.getText().toString();
        boolean bchk=checkBox.isChecked();
        if(!bchk)
        {
            //xóa mọi lưu trữ trước đó
            editor.clear();
        }
        else
        {
            //lưu vào editor
            editor.putString("manv", manv);
            editor.putString("tennv", tennv);
            editor.putBoolean("checked", bchk);

            //lấy dữ liệu từ SharePrefrences
             manv =  pre.getString("manv","");
             tennv = pre.getString("tennv","");
             bchk = pre.getBoolean("checked",true);
        }
        //chấp nhận lưu xuống file
        editor.commit();
    }
    /**
     * hàm đọc trạng thái đã lưu trước đó
     */
    public void restoringPreferences()
    {
        SharedPreferences pre=getSharedPreferences(prefname,MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        boolean bchk=pre.getBoolean("checked", false);
        if(bchk)
        {
            //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
            String manv=pre.getString("manv", "");
            String tennv=pre.getString("tennv", "");
            edtmanv.setText(manv);
            edttennv.setText(tennv);
        }
        checkBox.setChecked(bchk);
    }

}
