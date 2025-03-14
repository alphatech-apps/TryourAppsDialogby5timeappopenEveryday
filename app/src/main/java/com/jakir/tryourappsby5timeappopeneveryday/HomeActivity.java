package com.jakir.tryourappsby5timeappopeneveryday;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private final List<TryOurAppsBottomSheet.Appinfo> appList = new ArrayList<>();
    private boolean doubleBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new DoRemoteJob(this);  // Remote job for load background data

        // Back Pressed Callback
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                // for doubleBackPressed function
                if (doubleBackPressed) {
                    finish(); // Exit the app
                } else {
                    doubleBackPressed = true;
                    Toast.makeText(HomeActivity.this, "Press once again to exit", Toast.LENGTH_SHORT).show();

                    // Check if the rate dialog should be shown
                    if (new TryOurAppHelper().shouldShowTryAppDialog(HomeActivity.this, 6)) {  // openCountWant means how much time app open then try to show
                        new TryOurAppHelper().showTryOurAppsDialog(HomeActivity.this);
                    }
//                  else Toast.makeText(HomeActivity.this, "Exit Dialog Show", Toast.LENGTH_SHORT).show();

                    new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackPressed = false, 2000);
                }

/*                // for exit dialog function

                // Check if the rate dialog should be shown
                if (new TryOurAppHelper().shouldShowTryAppDialog(HomeActivity.this, 6)) {  // openCountWant means how much time app open then try to show
                    new TryOurAppHelper().showTryOurAppsDialog(HomeActivity.this);
                }else {
                    new Exit_Dialog(this).show();
                }*/
            }
        });
    }
}
