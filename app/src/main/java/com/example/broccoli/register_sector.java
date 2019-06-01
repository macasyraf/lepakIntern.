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

import java.util.HashMap;

public class register_sector extends AppCompatActivity
{
    EditText usernameField, emailField, passwordField, phoneField, matricField;
    Button registerButton, successButton, failedButton;
    Dialog viewSuccess, viewFailed;
    User user;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fontUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Century-Gothic.ttf");
        setContentView(R.layout.activity_register_sector);

        usernameField = findViewById(R.id.user_register);
        emailField = findViewById(R.id.email_register);
        passwordField = findViewById(R.id.password_register);
        phoneField = findViewById(R.id.phone_register);
        matricField = findViewById(R.id.matric_register);
        registerButton = findViewById(R.id.button_register);

        viewSuccess = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        viewSuccess.setContentView(R.layout.view_success);
        successButton = viewSuccess.findViewById(R.id.view_success);
        viewSuccess.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        viewFailed = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        viewFailed.setContentView(R.layout.view_failed);
        failedButton = viewFailed.findViewById(R.id.view_failed);
        viewFailed.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorBackground));

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username, email, password, phone, matric;

                username = usernameField.getText().toString();
                email = emailField.getText().toString();
                password = passwordField.getText().toString();
                phone = phoneField.getText().toString();
                matric = matricField.getText().toString();

                user = new User(username, email, password, phone, matric);
                insertData();
            }
        });

        successButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(register_sector.this, login_sector.class);
                register_sector.this.finish();
                startActivity(intent);
            }
        });

        failedButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewFailed.hide();
            }
        });
    }

    private void insertData()
    {
        @SuppressLint("StaticFieldLeak")
        class RegisterUser extends AsyncTask<Void, Void, String>
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
                hashMap.put("username", user.username);
                hashMap.put("email", user.email);
                hashMap.put("password", user.password);
                hashMap.put("phone", user.phone);
                hashMap.put("matric", user.matric);
                requestHandler requestHandler = new requestHandler();
                String framing = requestHandler.sendPostRequest("http://githubbers.com/sliice/register.php", hashMap);
                return framing;
            }

            @Override
            protected void onPostExecute(String framing)
            {
                super.onPostExecute(framing);

                if (framing.equalsIgnoreCase("success"))
                {
                    viewSuccess.show();
                }

                else if (framing.equalsIgnoreCase("nodata"))
                {
                    viewFailed.show();
                }

                else
                {
                    viewFailed.show();
                }
            }
        }

        RegisterUser registerUser = new RegisterUser();
        registerUser.execute();
    }
}
