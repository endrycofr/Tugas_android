package com.zakiabdullah.latihanstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ExternalActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FILENAME = "filenya1.txt"; // Corrected filename

//    public static final  String TAG = BuildConfig.APPLICATION_ID;
    public static final int REQUEST_CODE_STORAGE = 100;

    Button buatFile, ubahFile, bacaFile, deleteFile;
    public int selectEvent = 0;

    TextView TextBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        buatFile = findViewById(R.id.buttonBuatFile);
        ubahFile = findViewById(R.id.buttonUbahFile);
        bacaFile = findViewById(R.id.buttonBacaFile);
        deleteFile = findViewById(R.id.buttonHapusFile);
        TextBaca = findViewById(R.id.TextBaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        deleteFile.setOnClickListener(this);
    }

    public boolean periksaIzinPenyimpanan() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
                return false;
            }
        } else {
            return true; // Permissions are not required for older versions
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        if (requestCode == REQUEST_CODE_STORAGE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                jalankanPerintah(selectEvent);
//            } else {
//                Toast.makeText(this, "Izin penyimpanan ditolak.", Toast.LENGTH_SHORT).show();
//            }
//        }

        switch (requestCode) {
            case REQUEST_CODE_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    jalankanPerintah(selectEvent);
                }
                break;
        }
    }

    void buatFile() {
        String isiFile = "Isi file external: ini eksternal storage";
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file, true);
                outputStream.write(isiFile.getBytes());
                outputStream.flush();
                outputStream.close();

                Toast.makeText(this, "File eksternal berhasil dibuat", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Tidak dapat mengakses eksternal storage.", Toast.LENGTH_SHORT).show();
        }
    }

    void ubahFile() {
        String isiFile = "Isi Ubah File: update ubah successfull";

//        String state = Environment.getExternalStorageState();
//        if (Environment.MEDIA_MOUNTED.equals(state)) {
//            return;
//        }

        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false); // Append mode
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(this, "File eksternal berhasil diubah", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void bacaFile() {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
                Toast.makeText(this, "File eksternal berhasil dibaca", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
            TextBaca.setText(text.toString());
        }
    }


    void hapusFile() {
        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        if (file.exists()) {
            file.delete();
            Toast.makeText(this, "File eksternal berhasil dihapus", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.buttonBuatFile:
//            case R.id.buttonBacaFile:
//            case R.id.buttonUbahFile:
//            case R.id.buttonHapusFile:
//                if (periksaIzinPenyimpanan()) {
//                    selectEvent = view.getId();
//                    jalankanPerintah(view.getId());
//                }
//                break;
//        }
//    }
//
//    private void jalankanPerintah(int id) {
//        switch (id) {
//            case R.id.buttonBuatFile:
//                buatFile();
//                break;
//            case R.id.buttonBacaFile:
//                bacaFile();
//                break;
//            case R.id.buttonUbahFile:
//                ubahFile();
//                break;
//            case R.id.buttonHapusFile:
//                hapusFile();
//                break;
//        }
//    }
//}

    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.buttonBuatFile || viewId == R.id.buttonBacaFile || viewId == R.id.buttonUbahFile || viewId == R.id.buttonHapusFile) {
            if (periksaIzinPenyimpanan()) {
                selectEvent = viewId;
                jalankanPerintah(viewId);
            }
        }
    }

    private void jalankanPerintah(int id) {
        if (id == R.id.buttonBuatFile) {
            buatFile();
        } else if (id == R.id.buttonBacaFile) {
            bacaFile();
        } else if (id == R.id.buttonUbahFile) {
            ubahFile();
        } else if (id == R.id.buttonHapusFile) {
            hapusFile();
        }

    }
}