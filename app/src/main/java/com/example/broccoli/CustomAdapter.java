package com.example.broccoli;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAdapter extends SimpleAdapter
{

    private Context mContext;
    public LayoutInflater inflater = null;

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */

    public CustomAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
    {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        try
        {
            if(convertView == null)
            {
                view = inflater.inflate(R.layout.list_main_sector, null);
                HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
                TextView compName = view.findViewById(R.id.compTitle);
                TextView compLocation = view.findViewById(R.id.jobLocation);
                TextView compSalary = view.findViewById(R.id.compSalary);
                TextView compAvailability = view.findViewById(R.id.viewAvailable);
                ImageView imgComp = view.findViewById(R.id.jobImage);
                String dataName = (String) data.get("compname");
                String dataLoc = (String) data.get("complocation");
                String dataSalary = (String) data.get("compsalary");
                String dataAvail = (String) data.get("compavailability");
                String dataId = (String) data.get("compid");
                compName.setText(dataName);
                compLocation.setText(dataLoc);
                compSalary.setText(dataSalary);
                compAvailability.setText(dataAvail);
                String image_url = "http://githubbers.com/sliice/images/"+dataId+".jpg";
                Picasso.with(mContext).load(image_url).fit().into(imgComp);
            }
        }catch (IndexOutOfBoundsException e)
        {

        }

        return view;
    }
}
