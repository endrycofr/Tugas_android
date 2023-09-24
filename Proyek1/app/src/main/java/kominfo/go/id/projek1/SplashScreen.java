//package kominfo.go.id.projek1;
//
//import android.content.Intent;
//import android.os.Handler;
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//public class SplashScreen extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//        new Handler().postDelayed(() -> {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }, 5000);
//    }
//}

package kominfo.go.id.projek1;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_DURATION = 5000; // Durasi splash screen dalam milidetik (5 detik)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Menggunakan Handler untuk menunda perpindahan ke MainActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Menutup SplashScreen agar tidak bisa kembali ke splash screen
        }, SPLASH_DURATION);
    }
}
