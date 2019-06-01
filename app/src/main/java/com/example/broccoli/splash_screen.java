package com.example.broccoli;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class splash_screen extends AppCompatActivity
{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorBackground));
        loadingScreen();
    }

    private void loadingScreen()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(4500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                Intent intent = new Intent(splash_screen.this, login_sector.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
