package com.nexteducation.storagemechanism.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nexteducation.storagemechanism.shared_preference.SharedPreference;

/**
 * Created by next on 23/12/16.
 */
public class SQLite extends SQLiteOpenHelper
{
    private static final String TAG = "SQLiteDatabaseDemo";

    private static final String DATABASE_NAME = "Employee.db";

    private static final String TABLE_NAME = "Employee";

    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";
    private static final String COL3 = "ADDRESS";
    private static final String COL4 = "DESIGNATION";

    SharedPreference mPref;

    public SQLite(Context context)
    {
        super(context, DATABASE_NAME, null, 1);

        mPref = new SharedPreference(context);

        String getPref = mPref.getPreference("Employee_table");

        if(getPref == null || !getPref.equals("table created"))
            createTable();

    }

    private void createTable()
    {
        SQLiteDatabase sqLite = getWritableDatabase();

        sqLite.execSQL("create table Employee(" +
                COL1+" integer primary key autoincrement," +
                COL2+" text," +
                COL3+" text," +
                COL4+" text)");
        mPref.setPreferences("Employee_table", "table created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        /*sqLiteDatabase.execSQL("create table Employee(" +
                COL1+" integer," +
                COL2+" text," +
                COL3+" text," +
                COL4+" text)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean insertData(String name, String address, String designation)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //contentValues.put(COL1,  id);

        contentValues.put(COL2, name);

        contentValues.put(COL3, address);

        contentValues.put(COL4, designation);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getData()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();


        Cursor result = sqLiteDatabase.rawQuery("Select * from "+TABLE_NAME, null);

        return result;
    }

    public  int updateData(String id, String name, String lastName, String city)
    {
        int rowsAffected;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1,  id);

        contentValues.put(COL2, name);

        contentValues.put(COL3, lastName);

        contentValues.put(COL4, city);

        rowsAffected = sqLiteDatabase.update(TABLE_NAME, contentValues, COL1+" = ?", new String[] {id});

        return rowsAffected;
    }

    public int deleteData(String id)
    {
        int rowsAffected;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        rowsAffected = sqLiteDatabase.delete(TABLE_NAME, COL1+" = ?", new String[] {id});

        return rowsAffected;
    }
}