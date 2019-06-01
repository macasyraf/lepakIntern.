package com.example.broccoli;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class forgot_password extends AppCompatActivity
{
    Dialog verify;
    Button verifyButton, verifyHide;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fontUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Century-Gothic.ttf");
        setContentView(R.layout.activity_forgot_password);

        verifyButton = findViewById(R.id.verify_button);


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorBackground));

        verify = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        verify.setContentView(R.layout.popup_forgot);
        verifyHide = verify.findViewById(R.id.close_button);
        verify.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        verifyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                verify.show();
            }
        });

        verifyHide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                verify.hide();
                Intent intent = new Intent(forgot_password.this, login_sector.class);
                startActivity(intent);
            }
        });
    }
}
