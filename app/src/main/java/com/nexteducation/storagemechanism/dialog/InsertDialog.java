package com.nexteducation.storagemechanism.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nexteducation.storagemechanism.R;
import com.nexteducation.storagemechanism.file.FileHandling;
import com.nexteducation.storagemechanism.shared_preference.SharedPreference;
import com.nexteducation.storagemechanism.sqlite.SQLite;

/**
 * Created by Nadimuddin on 23/12/16.
 */
public class InsertDialog extends Dialog implements View.OnClickListener
{
    EditText mId, mName, mAddress, mDesignation;
    Button mInsertData, mCancel;
    String mMechanism;
    Context mContext;

    public static final String TAG = "InsertDialog";

    public InsertDialog(Context context, String mechanism)
    {
        super(context);
        mContext = context;
        mMechanism = mechanism;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);

        mId = (EditText)findViewById(R.id.id);
        mName = (EditText)findViewById(R.id.name);
        mAddress = (EditText)findViewById(R.id.address);
        mDesignation = (EditText)findViewById(R.id.designation);

        mInsertData = (Button)findViewById(R.id.insert_data);
        mCancel = (Button)findViewById(R.id.cancel);

        mInsertData.setOnClickListener(this);
        mCancel.setOnClickListener(this);

        if(mMechanism.equals("SQLite"))
        {
            mId.setHint("ID is autoincrement");
            mId.setKeyListener(null);
            mId.setFocusable(false);
            mId.setEnabled(false);
        }
    }

    /*protected InsertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public InsertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }*/

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.insert_data:
                boolean isInserted = false;

                String id = mId.getText().toString();
                String name = mName.getText().toString();
                String address = mAddress.getText().toString();
                String designation = mDesignation.getText().toString();

                switch (mMechanism)
                {
                    case "File":
                        FileHandling file = new FileHandling();
                        try {
                            isInserted = file.writeFile(id, name, address, designation);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //isInserted = Mechanisms.fileHandling(id, name, address, designation);
                        break;

                    case "SQLite":
                        SQLite sqLite = new SQLite(mContext);
                        isInserted = sqLite.insertData(name, address, designation);
                        break;

                    case "SharedPreference":
                        SharedPreference sharedPreference = new SharedPreference(mContext);
                        sharedPreference.setPreferences("id", Integer.parseInt(id));
                        sharedPreference.setPreferences("name", name);
                        sharedPreference.setPreferences("address", address);
                        sharedPreference.setPreferences("designation", designation);
                        isInserted = true;
                        break;
                }
                if(isInserted)
                    dismiss();
                break;

            case R.id.cancel:
                dismiss();
                break;
        }
    }
}
