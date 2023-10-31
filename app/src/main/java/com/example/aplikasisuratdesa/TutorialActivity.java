package com.example.aplikasisuratdesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);



    }
    public void backtohomeactivity2 (View view){
        Intent intent = new Intent(TutorialActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}