package com.nexteducation.storagemechanism.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nexteducation.storagemechanism.fragment.FragmentMechanism;

/**
 * Created by next on 23/12/16.
 */
public class MechanismAdapter extends FragmentPagerAdapter
{
    private static final int NUM_ITEMS = 3;
    private enum Mechanism{File, SQLite, SharedPreference}
    public MechanismAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        FragmentMechanism fragment = new FragmentMechanism();
        Bundle bundle = new Bundle();
        bundle.putString("mechanism", Mechanism.values()[position].toString());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return super.getPageTitle(position);
        return Mechanism.values()[position].toString();
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
