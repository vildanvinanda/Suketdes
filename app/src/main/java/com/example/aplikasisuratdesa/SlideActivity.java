package com.example.aplikasisuratdesa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SlideActivity extends AppCompatActivity {
    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btn_next,  btn_finish;
    int positionn = 0;
    Animation btAnim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        // make the activity on full screen
        View decorVIews = getWindow().getDecorView();

        decorVIews.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //when this activity is about to be launch we need to check if its opened before or not

        if (restorePrefData()){

            Intent mainActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(mainActivity);
            finish();

        }

        //hide action bar
        //getSupportActionBar().hide();

        // ini views
        tabIndicator = findViewById(R.id.tab_indicator);

        // fill list screen
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem(R.drawable.selamatdatang));
        mList.add(new ScreenItem(R.drawable.pusattutor));
        mList.add(new ScreenItem(R.drawable.exitpage));

        //btnnext
        btn_next = findViewById(R.id.btn_next);

        //btnfinish
        btn_finish = findViewById(R.id.btn_get_started);

        //setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);

        //setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        //next button click listener
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);

                }
                if (position == mList.size()) {//when we rech to the last screen
                    //TODO : show the GETSTARTED Button and hide the indicator and the next buttton

                    loadLastScreen();
                }
            }
        });

        // tablayout add change listener

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //get started button click listener
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open home activity
                Intent intent = new Intent (SlideActivity.this,HomeActivity.class);
                startActivity(intent);
                //also we need to save a boolean value to storage so nesxt time
                
                savePrefsData();
                finish();
            }
        });

    }

    private boolean restorePrefData(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;

    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();

    }

    //show the GETSTARTED Button and hide the indicator and the next button
            private void loadLastScreen() {

                btn_next.setVisibility(View.INVISIBLE);
                btn_finish.setVisibility(View.VISIBLE);
                tabIndicator.setVisibility(View.INVISIBLE);
                //TODO: ADD an animation the getstarted button
                //lets create the button animation
                //setup animator
                btn_finish.setAnimation(btnAnim);

            }



    }