package com.nexteducation.storagemechanism.model;

/**
 * Created by next on 23/12/16.
 */
public class Data
{
    int mId;
    String mName, mAddress, mDesignation;
    public Data(int id, String name, String address, String designation)
    {
        mId = id;
        mName = name;
        mAddress = address;
        mDesignation = designation;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getDesignation() {
        return mDesignation;
    }
}
