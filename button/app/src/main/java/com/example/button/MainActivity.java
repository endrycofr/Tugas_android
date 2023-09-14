package com.example.button;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isRed = false;
    private int originalBackgroundColor;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnOk = findViewById(R.id.ok);

        // Simpan warna asal latar belakang tombol
        originalBackgroundColor = Color.BLUE;

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRed) {
                    // Mengembalikan latar belakang ke warna asal
                    btnOk.setBackgroundColor(originalBackgroundColor);                }
                else {
                    // Mengubah latar belakang menjadi merah
                    btnOk.setBackgroundColor(Color.RED);
                }
                isRed = !isRed; // Toggle status
            }
        });
    }
}