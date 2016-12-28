package com.nexteducation.storagemechanism.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nadimuddin on 23/12/16.
 */
public class SharedPreference
{
    private static final String PREFERENCE = "Cam Scanner";

    static Context mContext;

    static SharedPreferences preferences;

    public SharedPreference(Context context)
    {
        mContext = context;

        preferences=context.getSharedPreferences(PREFERENCE,mContext.MODE_PRIVATE);
    }

    public String getPreference(String key)
    {
        return preferences.getString(key, null);
    }

    public int getIntPreferences(String key)
    {
        return preferences.getInt(key, 0);
    }

    public void setPreferences(String key, String stringToSet)
    {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(key, stringToSet);
        editor.commit();
        //editor.apply();
    }

    public void setPreferences(String key, int valueToSet)
    {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(key, valueToSet);

        editor.apply();
    }
}
