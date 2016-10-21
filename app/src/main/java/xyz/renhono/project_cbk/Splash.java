package xyz.renhono.project_cbk;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Splash extends AppCompatActivity {

    private final int DISPLAY_TIME = 2000;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);


        sp = getSharedPreferences("cbk", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                int in = sp.getInt("enter", 0);

                if (in == 1) {

                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Intent intent = new Intent(Splash.this, GuideActivity.class);

                    startActivity(intent);
                    finish();

                    SharedPreferences.Editor editor = sp.edit();

                    editor.putInt("enter", 1);

                    editor.commit();


                }


            }
        }, DISPLAY_TIME);


    }
}
