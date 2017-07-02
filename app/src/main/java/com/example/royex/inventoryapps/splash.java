package com.example.royex.inventoryapps;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Fragment fragment = new login();
////                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
////                ft.replace(R.id.content_frame, fragment);
////                ft.commit();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.content_frame, fragment, fragment.getClass().getSimpleName())
//                        .commit();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        },4000);
    }
}
