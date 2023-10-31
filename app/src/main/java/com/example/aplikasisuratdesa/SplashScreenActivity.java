package com.example.aplikasisuratdesa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View decorVIew = getWindow().getDecorView();

        decorVIew.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

//        Thread thread = new Thread(){
//            public void run() {
//                try {
//                    sleep(1000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }finally {
//                    startActivity(new Intent(SplashScreenActivity.this,HomeActivity.class));
//                }
//            }
//        };
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,SlideActivity.class));
            }
        },2000);

    }
}