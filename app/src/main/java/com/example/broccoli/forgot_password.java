package com.example.broccoli;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class forgot_password extends AppCompatActivity
{
    EditText verifyMail;
    Dialog verify, failure;
    Button verifyButton, verifyHide, failureHide;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fontUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Century-Gothic.ttf");
        setContentView(R.layout.activity_forgot_password);

        verifyButton = findViewById(R.id.verify_button);
        verifyMail = findViewById(R.id.email_recover);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorBackground));

        verify = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        verify.setContentView(R.layout.popup_forgot);
        verifyHide = verify.findViewById(R.id.exit_button);
        verify.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        failure = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        failure.setContentView(R.layout.popup_failure);
        failureHide = failure.findViewById(R.id.exit_button);
        failure.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        verifyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String forgetEmail = verifyMail.getText().toString();
                sendPassword(forgetEmail);

            }
        });

        failureHide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                failure.hide();
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

    private void sendPassword(final String forgotEmail)
    {
        class SendPassword extends AsyncTask<Void, String, String>
        {

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String, String> hashMap = new HashMap();
                hashMap.put("email", forgotEmail);
                requestHandler requestHandler = new requestHandler();
                String framing = requestHandler.sendPostRequest("http://githubbers.com/sliice/verify_email.php", hashMap);
                return framing;
            }

            @Override
            protected void onPostExecute(String framing)
            {
                super.onPostExecute(framing);
                if (framing.equalsIgnoreCase("success"))
                {
                    verify.show();
                }
                else
                {
                    failure.show();
                }
            }
        }

        SendPassword sendPassword = new SendPassword();
        sendPassword.execute();
    }
}
