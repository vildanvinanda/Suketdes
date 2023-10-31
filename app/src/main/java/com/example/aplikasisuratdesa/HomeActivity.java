package com.example.aplikasisuratdesa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Button btn_surat_penghasilan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_surat_penghasilan = findViewById(R.id.btn_surat_penghasilan);


                View decorVIew = getWindow().getDecorView();

        decorVIew.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


    }

    public void exit(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    public void suratketerangan(View view) {
        Intent intent = new Intent (HomeActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void btntoTutorial(View view) {
        Intent intent = new Intent (HomeActivity.this,TutorialActivity.class);
        startActivity(intent);
    }
    public void  igvildn (View view){
        Uri uri = Uri.parse("https://www.instagram.com/vildanvinanda/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.instagram.android");
        try{
            Toast.makeText(HomeActivity.this, "Instagram",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/vildanvinanda/")));
        }
    }
    public void  igmoh (View view){
        Uri uri = Uri.parse("https://www.instagram.com/zenmuhammad33/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.instagram.android");
        try{
            Toast.makeText(HomeActivity.this, "Instagram",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/zenmuhammad33/")));
        }
    }

}