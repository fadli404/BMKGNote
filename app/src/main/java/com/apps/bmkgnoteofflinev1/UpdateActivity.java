package com.apps.bmkgnoteofflinev1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText hari_input,tanggal_input,bulan_input,tahun_input,angin_permukaan_input,
            azimuth_input,elevasi_input,ketinggian_input;
    Button update_button,delete_button;
    String id,hari,tanggal,bulan,tahun,angin_permukaan,azimuth,elevasi,ketinggian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        hari_input = findViewById(R.id.hari_input_toUpdate);
        tanggal_input = findViewById(R.id.tanggal_input_toUpdate);
        bulan_input = findViewById(R.id.bulan_input_toUpdate);
        tahun_input = findViewById(R.id.tahun_input_toUpdate);
        angin_permukaan_input = findViewById(R.id.angin_permukaan_input_toUpdate);
        azimuth_input = findViewById(R.id.azimuth_input_toUpdate);
        elevasi_input = findViewById(R.id.elevasi_input_toUpdate);
        ketinggian_input = findViewById(R.id.ketinggian_input_toUpdate);

        update_button = findViewById(R.id.update_data);

        delete_button = findViewById(R.id.hapus_data);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(hari);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                hari = hari_input.getText().toString().trim();
                tanggal = tanggal_input.getText().toString().trim();
                bulan = bulan_input.getText().toString().trim();
                tahun = tahun_input.getText().toString().trim();
                angin_permukaan = angin_permukaan_input.getText().toString().trim();
                azimuth = azimuth_input.getText().toString().trim();
                elevasi = elevasi_input.getText().toString().trim();
                ketinggian = ketinggian_input.getText().toString().trim();
                myDB.updateData(id,hari,tanggal,bulan,tahun,angin_permukaan,azimuth,elevasi,ketinggian);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("hari")
                && getIntent().hasExtra("tanggal") && getIntent().hasExtra("bulan")
                && getIntent().hasExtra("tahun") && getIntent().hasExtra("angin_permukaan")
                && getIntent().hasExtra("azimuth") && getIntent().hasExtra("elevasi")
                && getIntent().hasExtra("ketinggian")) {

            id = getIntent().getStringExtra("id");
            hari = getIntent().getStringExtra("hari");
            tanggal = getIntent().getStringExtra("tanggal");
            bulan = getIntent().getStringExtra("bulan");
            tahun = getIntent().getStringExtra("tahun");
            angin_permukaan = getIntent().getStringExtra("angin_permukaan");
            azimuth = getIntent().getStringExtra("azimuth");
            elevasi = getIntent().getStringExtra("elevasi");
            ketinggian = getIntent().getStringExtra("ketinggian");

            hari_input.setText(hari);
            tanggal_input.setText(tanggal);
            bulan_input.setText(bulan);
            tahun_input.setText(tahun);
            angin_permukaan_input.setText(angin_permukaan);
            azimuth_input.setText(azimuth);
            elevasi_input.setText(elevasi);
            ketinggian_input.setText(ketinggian);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data");
        builder.setMessage("Apakah anda yakin ingin menghapus data untuk hari " +hari+" "+tanggal+" "+bulan+" "+tahun+" ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}