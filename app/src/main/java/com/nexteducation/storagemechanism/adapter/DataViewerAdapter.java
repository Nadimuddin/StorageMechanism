package com.nexteducation.storagemechanism.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nexteducation.storagemechanism.R;
import com.nexteducation.storagemechanism.model.Data;

import java.util.ArrayList;

/**
 * Created by next on 23/12/16.
 */
public class DataViewerAdapter extends BaseAdapter
{
    ArrayList<Data> mArrayList;
    LayoutInflater inflater;
    public DataViewerAdapter(Context context, ArrayList<Data> arrayList)
    {
        inflater = LayoutInflater.from(context);
        mArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if(view == null)
            view = inflater.inflate(R.layout.data, parent, false);

        TextView id = (TextView)view.findViewById(R.id.data_id);
        TextView name = (TextView)view.findViewById(R.id.data_name);
        TextView address = (TextView)view.findViewById(R.id.data_address);
        TextView designation = (TextView)view.findViewById(R.id.data_designation);

        Data data = mArrayList.get(position);

        id.setText(Integer.toString(data.getId()));
        name.setText(data.getName());
        address.setText(data.getAddress());
        designation.setText(data.getDesignation());
        return view;
    }
}
