package com.nexteducation.storagemechanism.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nexteducation.storagemechanism.DataViewer;
import com.nexteducation.storagemechanism.R;
import com.nexteducation.storagemechanism.dialog.DeleteDialog;
import com.nexteducation.storagemechanism.dialog.InsertDialog;

/**
 * Created by Nadimuddin on 23/12/16.
 */
public class FragmentMechanism extends Fragment implements View.OnClickListener
{
    public static final String TAG = "FragmentMechanism";
    Button mInsert, mDelete, mView;
    String mTitle;

    /*public FragmentMechanism(String title)
    {

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_mechanism, container, false);

        Bundle arg = getArguments();
        mTitle = arg.getString("mechanism");
        Log.i(TAG, "onCreateView::Title: "+mTitle);

        mInsert = (Button)view.findViewById(R.id.insert);
        mDelete = (Button)view.findViewById(R.id.delete);
        mView = (Button)view.findViewById(R.id.view);

        mInsert.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.insert:
                InsertDialog insert = new InsertDialog(getContext(), mTitle);
                insert.show();
                Log.i(TAG, "onClick: "+mTitle);
                break;

            case R.id.view:

                Intent intent = new Intent(getContext(), DataViewer.class);
                intent.putExtra("mechanism", mTitle);
                startActivity(intent);
                break;

            case R.id.delete:
                DeleteDialog delete = new DeleteDialog(getContext(), mTitle);
                delete.show();
                break;
        }
    }


}
