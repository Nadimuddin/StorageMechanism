package com.nexteducation.storagemechanism.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nexteducation.storagemechanism.R;
import com.nexteducation.storagemechanism.file.FileHandling;
import com.nexteducation.storagemechanism.shared_preference.SharedPreference;
import com.nexteducation.storagemechanism.sqlite.SQLite;

/**
 * Created by next on 26/12/16.
 */
public class DeleteDialog extends Dialog implements View.OnClickListener
{
    Context mContext;
    String mMechanism;
    EditText mGetId;
    Button mDeleteData;
    public DeleteDialog(Context context, String mechanism)
    {
        super(context);
        mContext = context;
        mMechanism = mechanism;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        mGetId = (EditText)findViewById(R.id.get_id);
        mDeleteData = (Button) findViewById(R.id.delete_data);

        mDeleteData.setOnClickListener(this);

        if(mMechanism.equals("SharedPreference"))
        {
            mGetId.setHint("Don't enter id just tap on delete");
            mGetId.setEnabled(false);
            mGetId.setFocusable(false);
            mGetId.setKeyListener(null);
        }
    }

    @Override
    public void onClick(View v)
    {
        String id = mGetId.getText().toString();
        switch (mMechanism) {
            case "SQLite":
                SQLite sqLite = new SQLite(mContext);
                sqLite.deleteData(id);
                break;

            case "File":
                FileHandling file = new FileHandling();
                file.deleteLine(Integer.parseInt(id));
                break;

            case "SharedPreference":
                SharedPreference sharedPreference = new SharedPreference(mContext);
                sharedPreference.setPreferences("id", 0);
                sharedPreference.setPreferences("name", "");
                sharedPreference.setPreferences("address", "");
                sharedPreference.setPreferences("designation", "");
                break;
        }
        dismiss();
    }
}
