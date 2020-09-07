package com.apps.bmkgnoteofflinev1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList data_id,data_hari,data_tanggal,data_bulan,data_tahun,data_angin_permukaan
            ,data_azimuth,data_elevasi,data_ketinggian;

    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList data_id,
                  ArrayList data_hari,
                  ArrayList data_tanggal,
                  ArrayList data_bulan,
                  ArrayList data_tahun,
                  ArrayList data_angin_permukaan,
                  ArrayList data_azimuth,
                  ArrayList data_elevasi,
                  ArrayList data_ketinggian){
        this.context = context;
        this.activity = activity;
        this.data_id = data_id;
        this.data_hari = data_hari;
        this.data_tanggal = data_tanggal;
        this.data_bulan = data_bulan;
        this.data_tahun = data_tahun;
        this.data_angin_permukaan = data_angin_permukaan;
        this.data_azimuth = data_azimuth;
        this.data_elevasi = data_elevasi;
        this.data_ketinggian = data_ketinggian;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.data_id_txt.setText(String.valueOf(data_id.get(position)));
        holder.data_hari_txt.setText(String.valueOf(data_hari.get(position)));
        holder.data_tanggal_txt.setText(String.valueOf(data_tanggal.get(position)));
        holder.data_bulan_txt.setText(String.valueOf(data_bulan.get(position)));
        holder.data_tahun_txt.setText(String.valueOf(data_tahun.get(position)));
        holder.data_angin_permukaan_txt.setText(String.valueOf(data_angin_permukaan.get(position)));
        holder.data_azimuth_txt.setText(String.valueOf(data_azimuth.get(position)));
        holder.data_elevasi_txt.setText(String.valueOf(data_elevasi.get(position)));
        holder.data_ketinggian_txt.setText(String.valueOf(data_ketinggian.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(data_id.get(position)));
                intent.putExtra("hari",String.valueOf(data_hari.get(position)));
                intent.putExtra("tanggal",String.valueOf(data_tanggal.get(position)));
                intent.putExtra("bulan",String.valueOf(data_bulan.get(position)));
                intent.putExtra("tahun",String.valueOf(data_tahun.get(position)));
                intent.putExtra("angin_permukaan",String.valueOf(data_angin_permukaan.get(position)));
                intent.putExtra("azimuth",String.valueOf(data_azimuth.get(position)));
                intent.putExtra("elevasi",String.valueOf(data_elevasi.get(position)));
                intent.putExtra("ketinggian",String.valueOf(data_ketinggian.get(position)));

                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView data_id_txt,data_hari_txt,data_tanggal_txt,data_bulan_txt,data_tahun_txt,
                data_angin_permukaan_txt, data_azimuth_txt,data_elevasi_txt,
                data_ketinggian_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data_id_txt = itemView.findViewById(R.id.id_txt);
            data_hari_txt = itemView.findViewById(R.id.hari_txt);
            data_tanggal_txt = itemView.findViewById(R.id.tanggal_txt);
            data_bulan_txt = itemView.findViewById(R.id.bulan_txt);
            data_tahun_txt = itemView.findViewById(R.id.tahun_txt);
            data_angin_permukaan_txt = itemView.findViewById(R.id.angin_permukaan_txt);
            data_azimuth_txt = itemView.findViewById(R.id.azimuth_txt);
            data_elevasi_txt = itemView.findViewById(R.id.elevasi_txt);
            data_ketinggian_txt = itemView.findViewById(R.id.ketinggian_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
