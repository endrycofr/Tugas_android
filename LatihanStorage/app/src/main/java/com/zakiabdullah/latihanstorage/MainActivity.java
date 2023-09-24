package com.zakiabdullah.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //pindah ke halaman internal
    public void pindahInternalStorage(View view)
    {
        Intent intent = new Intent (MainActivity.this, InternalActivity.class);
        startActivity(intent);
    }
    //pindah ke halaman eksternal
    public void pindahExternalStorage(View view)
    {
        Intent intent = new Intent (MainActivity.this, ExternalActivity.class);
        startActivity(intent);
    }
}