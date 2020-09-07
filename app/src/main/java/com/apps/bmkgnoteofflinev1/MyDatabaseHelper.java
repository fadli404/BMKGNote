package com.apps.bmkgnoteofflinev1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "data_bmkg.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "myListData";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_HARI = "hari";
    private static final String COLUMN_TANGGAL = "tanggal";
    private static final String COLUMN_BULAN = "bulan";
    private static final String COLUMN_TAHUN = "tahun";
    private static final String COLUMN_ANGIN_PERMUKAAN = "angin_permukaan";
    private static final String COLUMN_AZIMUTH = "azimuth";
    private static final String COLUMN_ELEVASI= "elevasi";
    private static final String COLUMN_KETINGGIAN = "ketinggian";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_HARI + " TEXT, " +
                COLUMN_TANGGAL + " INTEGER, " +
                COLUMN_BULAN + " TEXT, " +
                COLUMN_TAHUN + " INTEGER, " +
                COLUMN_ANGIN_PERMUKAAN + " INTEGER, " +
                COLUMN_AZIMUTH + " TEXT, " +
                COLUMN_ELEVASI + " TEXT, " +
                COLUMN_KETINGGIAN + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addData(String hari, int tanggal, String bulan, int tahun, int angin_permukaan ,
                 String azimuth, String elevasi , int ketinggian){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_HARI, hari);
        cv.put(COLUMN_TANGGAL, tanggal);
        cv.put(COLUMN_BULAN, bulan);
        cv.put(COLUMN_TAHUN, tahun);
        cv.put(COLUMN_ANGIN_PERMUKAAN, angin_permukaan);
        cv.put(COLUMN_AZIMUTH, azimuth);
        cv.put(COLUMN_ELEVASI, elevasi);
        cv.put(COLUMN_KETINGGIAN, ketinggian);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id, String hari,String tanggal,String bulan,String tahun,String angin_permukaan,
                    String azimuth,String elevasi,String ketinggian){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_HARI,hari);
        cv.put(COLUMN_TANGGAL,tanggal);
        cv.put(COLUMN_BULAN,bulan);
        cv.put(COLUMN_TAHUN,tahun);
        cv.put(COLUMN_ANGIN_PERMUKAAN,angin_permukaan);
        cv.put(COLUMN_AZIMUTH,azimuth);
        cv.put(COLUMN_ELEVASI,elevasi);
        cv.put(COLUMN_KETINGGIAN,ketinggian);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Update!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
