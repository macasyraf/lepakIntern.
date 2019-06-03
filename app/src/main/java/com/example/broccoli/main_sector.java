package com.example.broccoli;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class main_sector extends AppCompatActivity
{
    String email, username;
    ListView lvComp;
    Dialog viewComp;
    Button viewClose;
    ArrayList<HashMap<String, String>> compList;
    String allView = "Available";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_top:
                    return true;
                case R.id.navigation_browse:
                    return true;
                case R.id.navigation_profile:
                    Intent inProfile = new Intent(main_sector.this, profile_sector.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username",username);
                    bundle.putString("email",email);
                    inProfile.putExtras(bundle);
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
        setContentView(R.layout.activity_main_sector);
        lvComp = findViewById(R.id.jobList);
        loadCompany(allView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getString("username");
        email = bundle.getString("email");

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorField));

        BottomNavigationView navigation = findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        lvComp.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                showJobDetails(position);
            }
        });
    }

    private void showJobDetails(int position)
    {
        viewComp = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        viewComp.setContentView(R.layout.view_company_details);
        viewComp.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView officialName, officialSalary, compName, compWebsite, compContact, compLocation, compSalary, compTask, compAvailability;
        final ImageView compImage = viewComp.findViewById(R.id.compImage);
        final String compId = compList.get(position).get("compid");
        officialName = viewComp.findViewById(R.id.view_failure);
        officialSalary = viewComp.findViewById(R.id.view_Salary);
        compName = viewComp.findViewById(R.id.compName);
        compWebsite = viewComp.findViewById(R.id.compWebsite);
        compContact = viewComp.findViewById(R.id.compContact);
        compLocation = viewComp.findViewById(R.id.compLocation);
        compSalary = viewComp.findViewById(R.id.compSalary);
        compTask = viewComp.findViewById(R.id.compTask);
        compAvailability = viewComp.findViewById(R.id.compAvailability);

        officialName.setText(compList.get(position).get("compname"));
        officialSalary.setText(compList.get(position).get("compsalary"));
        compName.setText(compList.get(position).get("compname"));
        compWebsite.setText(compList.get(position).get("compwebsite"));
        compContact.setText(compList.get(position).get("compcontact"));
        compLocation.setText(compList.get(position).get("complocation"));
        compSalary.setText(compList.get(position).get("compsalary"));
        compTask.setText(compList.get(position).get("comptask"));
        compAvailability.setText(compList.get(position).get("compavailability"));

        viewClose = viewComp.findViewById(R.id.view_success);

        viewClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewComp.hide();
            }
        });


        Picasso.with(this).load("http://githubbers.com/sliice/images/"+compId+".jpg").memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).fit().into(compImage);
        viewComp.show();

    }

    private void loadCompany(final String availability)
    {
        class LoadCompany extends AsyncTask<Void, Void, String>
        {
            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("compavailability", availability);
                requestHandler requestHandler = new requestHandler();
                compList = new ArrayList<>();
                String s = requestHandler.sendPostRequest("http://githubbers.com/sliice/compload.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                compList.clear();
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray compArray = jsonObject.getJSONArray("companies");
                    Log.e("Marshmallow", jsonObject.toString());

                    for (int i = 0; i < compArray.length(); i++)
                    {
                        JSONObject c = compArray.getJSONObject(i);
                        String cid = c.getString("compid");
                        String cname = c.getString("compname");
                        String cwebsite = c.getString("compwebsite");
                        String ccontact = c.getString("compcontact");
                        String clocation = c.getString("complocation");
                        String csalary = c.getString("compsalary");
                        String ctask = c.getString("comptask");
                        String cavail = c.getString("compavailability");
                        HashMap<String, String> compListHash = new HashMap<>();
                        compListHash.put("compid", cid);
                        compListHash.put("compname", cname);
                        compListHash.put("compwebsite", cwebsite);
                        compListHash.put("compcontact", ccontact);
                        compListHash.put("complocation", clocation);
                        compListHash.put("compsalary", csalary);
                        compListHash.put("comptask", ctask);
                        compListHash.put("compavailability", cavail);
                        compList.add(compListHash);
                    }
                }catch (final JSONException e)
                {
                    Log.e("JSON-ERROR", e.toString());
                }

                ListAdapter adapter = new CustomAdapter(main_sector.this, compList, R.layout.list_main_sector, new String[]
                        {"compname", "complocation", "compsalary", "compavailability"}, new int[]
                        {R.id.compTitle, R.id.jobLocation, R.id.compSalary, R.id.viewAvailable});
                lvComp.setAdapter(adapter);
            }
        }

        LoadCompany loadCompany = new LoadCompany();
        loadCompany.execute();
    }
}
