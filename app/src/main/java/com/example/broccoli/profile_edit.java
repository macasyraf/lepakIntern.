package com.example.broccoli;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class profile_edit extends AppCompatActivity
{
    String email, username;
    Button buttonUpdate;
    EditText editEmail, editPhone, roldPassword;
    TextView textProfile, textMatric;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_top:
                    Intent inMain = new Intent(profile_edit.this, main_sector.class);
                    startActivity(inMain);
                    return true;
                case R.id.navigation_browse:
                    return true;
                case R.id.navigation_profile:
                    Intent inProfile = new Intent(profile_edit.this, profile_sector.class);
                    startActivity(inProfile);
                    return true;
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fontUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Century-Gothic.ttf");
        setContentView(R.layout.activity_profile_edit);

        textProfile = findViewById(R.id.textProfile);
        textProfile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mood_white, 0, 0 ,0);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        username = bundle.getString("username");
        email = bundle.getString("email");
        textMatric = findViewById(R.id.matric);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        roldPassword = findViewById(R.id.oldPassword);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        textMatric.setText(username);
        loadUserProfile();

        buttonUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newEmail = editEmail.getText().toString();
                String newPhone = editPhone.getText().toString();
                String oldPassword = roldPassword.getText().toString();
                dialogUpdate(newEmail, newPhone, oldPassword);
            }
        });

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorField));

        BottomNavigationView navigation = findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void loadUserProfile()
    {
        class LoadUserProfile extends AsyncTask<Void, Void, String>
        {

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", username);
                hashMap.put("email", email);
                requestHandler requestHandler = new requestHandler();
                String framing = requestHandler.sendPostRequest("http://githubbers.com/sliice/userload.php", hashMap);
                return framing;
            }

            @Override
            protected void onPostExecute(String framing)
            {
                super.onPostExecute(framing);
                try
                {
                    JSONObject jsonObject = new JSONObject(framing);
                    JSONArray userArray = jsonObject.getJSONArray("user");
                    JSONObject userObject = userArray.getJSONObject(0);
                    username = userObject.getString("username");
                    email = userObject.getString("email");
                }
                catch (JSONException e)
                {

                }

                editEmail.setText(email);
            }
        }

        LoadUserProfile loadUserProfile = new LoadUserProfile();
        loadUserProfile.execute();
    }

    void updateProfile(final String newEmail, final String newPhone, final String oldPassword)
    {
        class UpdateProfile extends AsyncTask<Void, Void, String>
        {
            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", username);
                hashMap.put("email", newEmail);
                hashMap.put("phone", newPhone);
                hashMap.put("oldpass", oldPassword);
                requestHandler requestHandler = new requestHandler();
                String framing = requestHandler.sendPostRequest("http://githubbers.com/sliice/userupdate.php", hashMap);
                return framing;
            }

            @Override
            protected void onPostExecute(String framing)
            {
                super.onPostExecute(framing);
                if (framing.equalsIgnoreCase("success"))
                {
                    Toast.makeText(profile_edit.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(profile_edit.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }

        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.execute();
    }

    private void dialogUpdate(final String newemail, final String newphone, final String oldpass) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Profile Changes");

        alertDialogBuilder
                .setMessage("Update your profile?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        updateProfile(newemail, newphone, oldpass);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
