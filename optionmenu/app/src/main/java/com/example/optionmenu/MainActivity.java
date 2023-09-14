package com.example.optionmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Homepage");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu); // Assuming you have a menu XML file named main_menu.xml
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection using if-else statements
        if (item.getItemId() == R.id.setting) {
            Intent intent = new Intent(MainActivity.this, setting.class); // Assuming you have a SettingActivity
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Selected Setting", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.more) {
            Intent intent = new Intent(MainActivity.this, more.class); // Assuming you have a MoreActivity
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Selected More", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
