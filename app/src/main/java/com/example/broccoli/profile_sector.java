package com.example.broccoli;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_sector extends AppCompatActivity
{
    CircleImageView profilePicture;
    String email, username;
    TextView viewEmail, viewUsername, editProfile, viewWishlisted, viewAbout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_top:
                    Intent inMain = new Intent(profile_sector.this, main_sector.class);
                    startActivity(inMain);
                    return true;
                case R.id.navigation_browse:
                    return true;
                case R.id.navigation_profile:
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
        setContentView(R.layout.activity_profile_sector);

        profilePicture = findViewById(R.id.profilePicture);

        editProfile = findViewById(R.id.editProfile);
        editProfile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mood_white, 0, 0 ,0);
        viewWishlisted = findViewById(R.id.wishlisted);
        viewWishlisted.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cake_white, 0, 0 ,0);
        viewAbout = findViewById(R.id.about);
        viewAbout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_whatshot_white, 0, 0 ,0);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        username = bundle.getString("username");
        email = bundle.getString("email");

        viewUsername = findViewById(R.id.username);
        viewEmail = findViewById(R.id.email);

        viewUsername.setText(username);
        viewEmail.setText(email);

        String image_url = "http://githubbers.com/sliice/images/profile.png";
        Picasso.with(this).load(image_url).resize(100, 100).into(profilePicture);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorField));

        BottomNavigationView navigation = findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        editProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent inProfile = new Intent(profile_sector.this, profile_edit.class);
                Bundle bundlel = new Bundle();
                bundlel.putString("username", username);
                bundlel.putString("email", email);

                inProfile.putExtras(bundlel);
                startActivity(inProfile);
            }
        });
    }

}
