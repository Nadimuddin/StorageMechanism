package com.nexteducation.storagemechanism.mechanism;

import android.os.Environment;
import android.util.Log;

import com.nexteducation.storagemechanism.file.FileHandling;

/**
 * Created by Nadimuddin on 23/12/16.
 */
public class Mechanisms
{
    public static final String TAG = "Mechanism";
    /*public static boolean fileHandling(String id, String name,String address, String designation)
    {
        FileHandling file = new FileHandling();
        String directory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DemoFolder";

        boolean isWriteCompleted = false;

        Log.i(TAG, "onClick: "+directory);

        String newData = id+" "+name+" "+address+" "+designation;

        try {
            String oldData = file.readFile();

            String data;
            if(oldData != null)
                data = oldData+"\n"+newData;
            else
                data = newData;

            isWriteCompleted = file.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isWriteCompleted;
    }*/

    public void SQLite()
    {

    }

    public void sharedPreference()
    {

    }
}
