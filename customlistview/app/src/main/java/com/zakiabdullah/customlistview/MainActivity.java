package com.zakiabdullah.customlistview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String negara[] = {"Indonesia", "Brunei", "Vietnam", "Laos", "Amerika" ,"India", "Jepan", "Belanda", "Burma", "Australia", "Spanyol", "Turkiye"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView, negara);
        listView.setAdapter(arrayAdapter);

        // Menambahkan pendengar klik pada setiap item di ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Mengambil teks dari item yang diklik
                String selectedItem = negara[position];
                // Menampilkan pesan toast
                Toast.makeText(MainActivity.this, "Anda memilih: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}