package com.nexteducation.storagemechanism;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nexteducation.storagemechanism.adapter.MechanismAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    ViewPager mViewPager;
    Button mInsert, mDelete, mView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager)findViewById(R.id.view_pager);

        MechanismAdapter adapter = new MechanismAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        /*mInsert = (Button)findViewById(R.id.insert);
        mDelete = (Button)findViewById(R.id.delete);
        mView = (Button)findViewById(R.id.view);

        mInsert.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mView.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v)
    {
        /*switch (v.getId())
        {
            case R.id.insert:
                InsertDialog insert = new InsertDialog(MainActivity.this);
                insert.show();
                break;

            case R.id.view:
                break;

            case R.id.delete:
                break;
        }*/
    }
}
