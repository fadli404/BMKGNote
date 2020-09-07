package com.apps.bmkgnoteofflinev1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(MainActivity.this, MenuUtama.class));
                }
            }
        };
        thread.start();
    }
}