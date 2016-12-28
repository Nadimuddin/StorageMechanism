package com.nexteducation.storagemechanism;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nexteducation.storagemechanism.adapter.DataViewerAdapter;
import com.nexteducation.storagemechanism.file.FileHandling;
import com.nexteducation.storagemechanism.mechanism.Mechanisms;
import com.nexteducation.storagemechanism.model.Data;
import com.nexteducation.storagemechanism.shared_preference.SharedPreference;
import com.nexteducation.storagemechanism.sqlite.SQLite;

import java.util.ArrayList;
import java.util.Currency;

/**
 * Created by next on 23/12/16.
 */
public class DataViewer extends AppCompatActivity
{
    ListView mListView;
    ArrayList<Data> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_viewer);

        String mechanism = getIntent().getStringExtra("mechanism");
        int id;
        String name, address, designation;

        mListView = (ListView)findViewById(R.id.list_view);

        mArrayList = new ArrayList<>();

        if (mechanism.equals("File"))
        {
            String fileContent = null;
            FileHandling file = new FileHandling();
            try {
                fileContent = file.readFile();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String lines[] = fileContent.split("\n");
            for(int i=0; i<lines.length; i++)
            {
                String data[] = lines[i].split(" ");

                id = Integer.parseInt(data[0]);
                name = data[1];
                address = data[2];
                designation = data[3];
                mArrayList.add(new Data(id, name, address, designation));
            }
        }

        else if(mechanism.equals("SQLite"))
        {
            SQLite sqLite = new SQLite(this);

            Cursor cursor = sqLite.getData();

            while (cursor.moveToNext())
            {
                id = cursor.getInt(0);
                name = cursor.getString(1);
                address = cursor.getString(2);
                designation = cursor.getString(3);

                mArrayList.add(new Data(id, name, address, designation));
            }
        }

        else if(mechanism.equals("SharedPreference"))
        {
            SharedPreference sharedPreference = new SharedPreference(this);
            id = sharedPreference.getIntPreferences("id");
            name = sharedPreference.getPreference("name");
            address = sharedPreference.getPreference("address");
            designation = sharedPreference.getPreference("designation");

            if(id!=0 && name!=null && address!=null && designation!=null)
                mArrayList.add(new Data(id, name, address, designation));
        }

        DataViewerAdapter adapter = new DataViewerAdapter(this, mArrayList);
        mListView.setAdapter(adapter);

    }
}
