package com.apps.bmkgnoteofflinev1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText hari_input, tanggal_input, bulan_input, tahun_input,
            angin_permukaan_input,
            azimuth_input, elevasi_input, ketinggian_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        hari_input = findViewById(R.id.hari_input);
        tanggal_input = findViewById(R.id.tanggal_input);
        bulan_input = findViewById(R.id.bulan_input);
        tahun_input = findViewById(R.id.tahun_input);
        angin_permukaan_input = findViewById(R.id.angin_permukaan_input);
        azimuth_input = findViewById(R.id.azimuth_input);
        elevasi_input = findViewById(R.id.elevasi_input);
        ketinggian_input = findViewById(R.id.ketinggian_input);

        add_button = findViewById(R.id.add_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addData(hari_input.getText().toString().trim(),
                        Integer.valueOf(tanggal_input.getText().toString().trim()),
                        (bulan_input.getText().toString().trim()),
                        Integer.valueOf(tahun_input.getText().toString().trim()),
                        Integer.valueOf(angin_permukaan_input.getText().toString().trim()),
                        (azimuth_input.getText().toString().trim()),
                        (elevasi_input.getText().toString().trim()),
                        Integer.valueOf(ketinggian_input.getText().toString().trim()));
                Intent intent = new Intent(AddActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
}