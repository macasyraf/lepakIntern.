package com.example.broccoli;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import java.util.HashMap;

public class login_sector extends AppCompatActivity
{
    TextView forgotPassword;
    EditText emailField, passwordField;
    Button buttonRegister, buttonLogin, buttonClose;
    Dialog failed;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fontUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Century-Gothic.ttf");
        setContentView(R.layout.activity_login_sector);

        forgotPassword = findViewById(R.id.password_recover);
        emailField = findViewById(R.id.insert_email);
        passwordField = findViewById(R.id.insert_password);
        buttonRegister = findViewById(R.id.register_button);
        buttonLogin = findViewById(R.id.login_button);

        failed = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        failed.setContentView(R.layout.popup_failed);
        buttonClose = failed.findViewById(R.id.close_button);
        failed.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorBackground));
        
        forgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(login_sector.this, forgot_password.class);
                startActivity(intent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(login_sector.this, register_sector.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                loginUser(email, password);
            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                failed.hide();
            }
        });
    }

    private void loginUser(final String email, final String password)
    {
        @SuppressLint("StaticFieldLeak")
        class LoginUser extends AsyncTask<Void, Void, String>
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("email", email);
                hashMap.put("password", password);
                requestHandler requestHandler = new requestHandler();
                String framing = requestHandler.sendPostRequest("http://githubbers.com/sliice/login.php", hashMap);
                return framing;
            }

            @Override
            protected void onPostExecute(String framing)
            {
                super.onPostExecute(framing);

                if (framing.equalsIgnoreCase("failed"))
                {
                    failed.show();
                }

                else if (framing.length() > 7)
                {
                    Intent intent = new Intent(login_sector.this, main_sector.class);
                    startActivity(intent);
                    finish();
                }
            }
        }

        LoginUser loginUser = new LoginUser();
        loginUser.execute();
    }
}
