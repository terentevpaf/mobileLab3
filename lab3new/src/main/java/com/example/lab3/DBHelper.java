package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 14;
    public static final String DATABASE_NAME = "usersDb";
    public static final String TABLE_USERS = "users";

    public static final String KEY_ID = "_id";
    public static final String KEY_NF = "fam";
    public static final String KEY_NI = "name";
    public static final String KEY_NO = "otch";
    public static final String KEY_DATE = "date";

    List<String> users = new ArrayList<String>();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + "(" + KEY_ID
                + " integer primary key," + KEY_NF + " text," + KEY_NI + " text," + KEY_NO + " text," + KEY_DATE + " text" + ")");

        if(users.size() > 0)
        {
            StringBuilder id;
            StringBuilder name;
            StringBuilder date;
            StringBuilder F;
            StringBuilder N;
            StringBuilder O;
            int gindex = 0;
            for (int index = 0; index < users.size(); index++)
            {
                id = new StringBuilder();
                name = new StringBuilder();
                date = new StringBuilder();
                F = new StringBuilder();
                N = new StringBuilder();
                O = new StringBuilder();

                gindex = 0;
                for (String retval : users.get(index).split("~")) {
                    if(gindex == 0)
                        id.append(retval);
                    else if(gindex == 1)
                        name.append(retval);
                    else if(gindex == 2)
                        date.append(retval);
                    gindex++;
                }

                gindex = 0;
                for (String retval : name.toString().split(" ")) {
                    if(gindex == 0)
                        F.append(retval);
                    else if(gindex == 1)
                        N.append(retval);
                    else if(gindex == 2)
                        O.append(retval);
                    gindex++;
                }

                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_NF, F.toString());
                contentValues.put(DBHelper.KEY_NI, N.toString());
                contentValues.put(DBHelper.KEY_NO, O.toString());
                contentValues.put(DBHelper.KEY_DATE, date.toString());
                db.insert(DBHelper.TABLE_USERS, null, contentValues);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("drop table if exists " + TABLE_USERS);
        //SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor c = db.query(DBHelper.TABLE_USERS, null, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            if (c.moveToFirst()) {
                while(!c.isAfterLast()) { // If you use c.moveToNext() here, you will bypass the first row, which is WRONG
                    String id = c.getString(c.getColumnIndex("_id"));
                    String name = c.getString(c.getColumnIndex("name"));
                    String date = c.getString(c.getColumnIndex("date"));
                    users.add(id + "~" + name + "~" + date);
                    c.moveToNext();
                }
            }
            c.close();
        }
        db.execSQL("drop table if exists " + TABLE_USERS);
        onCreate(db);
    }
}
