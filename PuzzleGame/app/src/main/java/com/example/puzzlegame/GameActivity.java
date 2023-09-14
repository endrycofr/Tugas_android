package com.example.puzzlegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private int emptyX = 3;
    private int emptyY = 3;
    private RelativeLayout group;
    private Button[][] buttons;
    private int[] tiles;
    private TextView textViewTime;
    private Timer timer;
    private  int timeCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        loadViews();
        loadNumbers();
        generateNumbers();
        loadDataToViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Assuming you have a menu XML file named main_menu.xml
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection using if-else statements
        if (item.getItemId() == R.id.action_shuffle) {
            Toast.makeText(GameActivity.this, "Acak Permainan", Toast.LENGTH_SHORT).show();
            recreate();
            return true;
        } else if (item.getItemId() == R.id.action_exit) {
            Intent intent = new Intent(GameActivity.this, MainActivity.class); // Assuming you have a MainActivity
            startActivity(intent);
            Toast.makeText(GameActivity.this, "Keluar dari Permainan", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void recreateGame() {
        // Memuat ulang tampilan permainan
        loadNumbers();
        generateNumbers();
        loadDataToViews();
        timeCount = 0;


        // Mengatur setiap tombol non-klikable
        for (int i = 0; i < group.getChildCount(); i++) {
            buttons[i / 4][i % 4].setClickable(false);
        }
    }


    // Load data to views
    private void loadDataToViews() {
        emptyX = 3;
        emptyY = 3;
        for (int i = 0; i < group.getChildCount() - 1; i++) {
            buttons[i / 4][i % 4].setText(String.valueOf(tiles[i]));
            buttons[i / 4][i % 4].setBackgroundResource(android.R.drawable.btn_default);
        }
        buttons[emptyX][emptyY].setText("");
        buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
    }

    // Generate random numbers
    private void generateNumbers() {
        int n = 15;
        Random random = new Random();
        while (n > 1) {
            int randomNum = random.nextInt(n--);
            int temp = tiles[randomNum];
            tiles[randomNum] = tiles[n];
            tiles[n] = temp;
        }
        if (!isSolvable()) {
            generateNumbers();
        }
    }

    // Check if the puzzle is solvable
    private boolean isSolvable() {
        int countInversions = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < i; j++) {
                if (tiles[j] > tiles[i]) {
                    countInversions++;
                }
            }
        }
        return countInversions % 2 == 0;
    }

    // Load numbers to tiles array
    private void loadNumbers() {
        tiles = new int[16];
        for (int i = 0; i < group.getChildCount() - 1; i++) {
            tiles[i] = i + 1;
        }
    }
    private void loadTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeCount++;
                setTime(timeCount);
            }
        }, 1000, 1000);
    }
    private  void  setTime (int timeCount){
        int second = timeCount % 60 ;
        int hour = timeCount /3600;
        int minute = (timeCount-hour*3600)/60;
        textViewTime.setText((String.format("Time : %02d:%02d:%02d",hour,minute,second )));
    }
    // Load views
    private void loadViews() {
        group = findViewById(R.id.group);
        textViewTime= findViewById(R.id.text_view_time);
        loadTimer();

        buttons = new Button[4][4];

        for (int i = 0; i < group.getChildCount(); i++) {
            buttons[i / 4][i % 4] = (Button) group.getChildAt(i);
        }
    }

    // Handle button clicks
    public void buttonClick(View view) {
        Button button = (Button) view;
        int x = button.getTag().toString().charAt(0) - '0';
        int y = button.getTag().toString().charAt(1) - '0';

        if ((Math.abs(emptyX - x) == 1 && emptyY == y) || Math.abs(emptyY - y) == 1 && emptyX == x) {
            buttons[emptyX][emptyY].setText(button.getText().toString());
            buttons[emptyX][emptyY].setBackgroundResource(android.R.drawable.btn_default);
            button.setText("");
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
            emptyX = x;
            emptyY = y;
            checkWin();
        }
    }

    // Check if the puzzle is solved
    private void checkWin() {
        boolean isWin = false;
        if (emptyX == 3 && emptyY == 3) {
            for (int i = 0; i < group.getChildCount() - 1; i++) {
                if (buttons[i / 4][i % 4].getText().toString().equals(String.valueOf(i + 1))) {
                    isWin = true;
                } else {
                    isWin = false;
                    break;
                }
            }
        }
        if (isWin) {
            Toast.makeText(this, "Win!!", Toast.LENGTH_LONG).show();
            for (int i = 0; i < group.getChildCount(); i++) {
                buttons[i / 4][i % 4].setClickable(false);
            }
            timer.cancel();
        }
    }
}
