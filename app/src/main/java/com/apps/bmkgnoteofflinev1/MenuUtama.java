package com.apps.bmkgnoteofflinev1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MenuUtama extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    TextView nodata;

    MyDatabaseHelper myDB;
    ArrayList<String> data_id,data_hari,data_tanggal,data_bulan,data_tahun,data_angin_permukaan,
            data_azimuth,data_elevasi,data_ketinggian;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        nodata = findViewById(R.id.nodata);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, AddActivity.class);
                startActivity(intent);
            }
        });
        myDB = new MyDatabaseHelper(MenuUtama.this);
        data_id = new ArrayList<>();
        data_hari = new ArrayList<>();
        data_tanggal = new ArrayList<>();
        data_bulan = new ArrayList<>();
        data_tahun = new ArrayList<>();
        data_angin_permukaan = new ArrayList<>();
        data_azimuth = new ArrayList<>();
        data_elevasi = new ArrayList<>();
        data_ketinggian = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(MenuUtama.this,this,data_id,data_hari,data_tanggal,
                data_bulan,data_tahun,data_angin_permukaan,
                data_azimuth,data_elevasi,data_ketinggian);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MenuUtama.this));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount()==0){
            nodata.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                data_id.add(cursor.getString(0));
                data_hari.add(cursor.getString(1));
                data_tanggal.add(cursor.getString(2));
                data_bulan.add(cursor.getString(3));
                data_tahun.add(cursor.getString(4));
                data_angin_permukaan.add(cursor.getString(5));
                data_azimuth.add(cursor.getString(6));
                data_elevasi.add(cursor.getString(7));
                data_ketinggian.add(cursor.getString(8));
            }
            nodata.setVisibility(View.GONE);
        }
    }
}