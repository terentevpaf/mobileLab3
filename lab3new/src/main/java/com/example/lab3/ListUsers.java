package com.example.lab3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListUsers extends AppCompatActivity {

    private DBHelper dbHelper;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        TableLayout tableLayout = (TableLayout)findViewById(R.id.tableUsers);
        tableLayout.removeAllViews();

        TableRow FtableRow = new TableRow(this);
        FtableRow.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        TextView textview11 = new TextView(this);
        textview11.setText("Номер");
        textview11.setGravity(Gravity.CENTER_HORIZONTAL);
        FtableRow.addView(textview11, 0);
        TextView textview22 = new TextView(this);
        textview22.setText("Фамилия");
        textview22.setGravity(Gravity.CENTER_HORIZONTAL);
        FtableRow.addView(textview22, 1);
        TextView textview33 = new TextView(this);
        textview33.setText("Имя");
        textview33.setGravity(Gravity.CENTER_HORIZONTAL);
        FtableRow.addView(textview33, 2);
        TextView textview44 = new TextView(this);
        textview44.setText("Отчество");
        textview44.setGravity(Gravity.CENTER_HORIZONTAL);
        FtableRow.addView(textview44, 3);
        TextView textview55 = new TextView(this);
        textview55.setText("Дата");
        textview55.setGravity(Gravity.CENTER_HORIZONTAL);
        FtableRow.addView(textview55, 4);
        tableLayout.addView(FtableRow, 0);

        Cursor c = database.query(DBHelper.TABLE_USERS, null, null, null, null, null, null);

        if (c != null) {

            c.moveToFirst();
            int index = 1;
            if (c.moveToFirst()) {
                while(!c.isAfterLast()) { // If you use c.moveToNext() here, you will bypass the first row, which is WRONG

                    TableRow tableRow = new TableRow(this);
                    tableRow.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                    String id = c.getString(c.getColumnIndex("_id"));
                    String fam = c.getString(c.getColumnIndex("fam"));
                    String name = c.getString(c.getColumnIndex("name"));
                    String otch = c.getString(c.getColumnIndex("otch"));
                    String date = c.getString(c.getColumnIndex("date"));

                    TextView textview1 = new TextView(this);
                    textview1.setText(id);
                    textview1.setGravity(Gravity.CENTER_HORIZONTAL);
                    tableRow.addView(textview1, 0);

                    TextView textview2 = new TextView(this);
                    textview2.setText(fam);
                    textview2.setGravity(Gravity.CENTER_HORIZONTAL);
                    tableRow.addView(textview2, 1);

                    TextView textview3 = new TextView(this);
                    textview3.setText(name);
                    textview3.setGravity(Gravity.CENTER_HORIZONTAL);
                    tableRow.addView(textview3, 2);

                    TextView textview4 = new TextView(this);
                    textview4.setText(otch);
                    textview4.setGravity(Gravity.CENTER_HORIZONTAL);
                    tableRow.addView(textview4, 3);

                    TextView textview5 = new TextView(this);
                    textview5.setText(date);
                    textview5.setGravity(Gravity.CENTER_HORIZONTAL);
                    tableRow.addView(textview5, 4);

                    c.moveToNext();

                    tableLayout.addView(tableRow, index);
                    index++;
                }
            }
            c.close();
        }
        database.close();
    }
}
