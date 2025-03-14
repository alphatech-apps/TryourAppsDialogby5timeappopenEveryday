package com.jakir.tryourappsby5timeappopeneveryday;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv = findViewById(R.id.tv);
        tv.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

        countAppOpen();

        // 10 seconds delay to start a new activity
        new Handler().postDelayed(() -> {
            // Intent to start new activity
            Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
            startActivity(intent);

            finish();
        }, 2000);


    }

    private void countAppOpen() {
        Util.countAppOpen(this);
        Toast.makeText(this, "Opened today: " + String.valueOf(Util.getCountAppOpen(this)), Toast.LENGTH_SHORT).show();
    }
}