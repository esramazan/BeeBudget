package com.example.beebudget.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.beebudget.Activitys.Giris_Activity;
import com.example.beebudget.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread budget_logo_anim = new Thread()
        { @Override
            public void run()
            {
                ImageView b5=findViewById(R.id.budget_logo);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_anim);
                b5.startAnimation(animation);
            }
        };
        budget_logo_anim.start();

        Thread ana_menu_ac = new Thread()
        { @Override
        public void run()
        {
            try
            {
                sleep(2000);
                Intent intent = new Intent(getApplicationContext(), Giris_Activity.class);
                startActivity(intent);
                finish();
                super.run();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        };
        ana_menu_ac.start();

    }
}