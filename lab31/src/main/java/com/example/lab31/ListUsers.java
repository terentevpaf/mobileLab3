package com.example.lab31;


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
/*
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        TextView NOMER = new TextView(this);
        NOMER.setText("Номер");
        tableRow.addView(NOMER, 0);

        TextView NAME = new TextView(this);
        NOMER.setText("ФИО");
        tableRow.addView(NAME, 1);

        TextView DATE = new TextView(this);
        NOMER.setText("Дата");
        tableRow.addView(DATE, 2);

        tableLayout.addView(tableRow, 0);
*/
        Cursor c = database.query(DBHelper.TABLE_USERS, null, null, null, null, null, null);

        if (c != null) {

            //more to the first row
            c.moveToFirst();

            int index = 0;

            if (c.moveToFirst()) {
                while(!c.isAfterLast()) { // If you use c.moveToNext() here, you will bypass the first row, which is WRONG

                    TableRow tableRow = new TableRow(this);
                    tableRow.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                    String id = c.getString(c.getColumnIndex("_id"));
                    String name = c.getString(c.getColumnIndex("name"));
                    String date = c.getString(c.getColumnIndex("date"));


                    TextView textview1 = new TextView(this);
                    textview1.setText(id);
                    textview1.setGravity(Gravity.CENTER_HORIZONTAL);
                    tableRow.addView(textview1, 0);

                    TextView textview2 = new TextView(this);
                    textview2.setText(name);
                    textview2.setGravity(Gravity.CENTER_HORIZONTAL);
                    tableRow.addView(textview2, 1);

                    TextView textview3 = new TextView(this);
                    textview3.setText(date);
                    textview3.setGravity(Gravity.CENTER_HORIZONTAL);
                    tableRow.addView(textview3, 2);

                    c.moveToNext();

                    tableLayout.addView(tableRow, index);
                    index++;
                }
                //tableLayout.addView(tableRow, new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            }

            /*
            for (int i = 0; i < cursor.getCount(); i++) {



                for(int j = 0; j < cursor.getColumnNames().length; j++)
                {
                    TextView textview = new TextView(this);
                    textview.setText(cursor.getString(j));
                    textview.setGravity(Gravity.CENTER_HORIZONTAL);
                    tr1.addView(textview);
                }



                //add a new line carriage return

                if(tr1.getParent() != null) {
                    ((ViewGroup)tr1.getParent()).removeView(tr1); // <- fix
                }
                tl.addView(tr1, new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                //move to the next row
                cursor.moveToNext();
            }
            //close the cursor
            */
            c.close();

        }

        /*
        for(int index = 0; index < 5; index++)
        {
            TableRow tr1 = new TableRow(this);
            tr1.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 3; j++) {
                TextView textview = new TextView(this);
                textview.setText("asdasd");
                textview.setGravity(Gravity.CENTER_HORIZONTAL);
                tr1.addView(textview);
            }
            tl.addView(tr1, new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }*/
        database.close();
    }
}
