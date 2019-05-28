package com.example.lab3;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.bListUsers);
        btn1.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.bAddUser);
        btn2.setOnClickListener(this);

        Button btn3 = (Button) findViewById(R.id.bEditLastUser);
        btn3.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.close();
    }

    public void addUser(int countUsers)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        List<String> users = new ArrayList<>();
        users.add("Аскандер Стивен Самах Саад");
        users.add("Исхак Керолос Камал");
        users.add("Акжигитов Радмир Русланович");
        users.add("Чимидов Эльвек Эренцович");
        users.add("Гумчина Арина Александровна");
        users.add("Бранченская Анна Михайловна");
        users.add("Подорожник Олег Михайлович");
        users.add("Хисамбиев Альберт Рашидович");
        users.add("Хисамбиев Рашид Альбертович");

        Random rnd = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder F;
        StringBuilder N;
        StringBuilder O;
        int gindex = 0;
        for (int index = 0; index < countUsers; index++)
        {
            ContentValues contentValues = new ContentValues();

            F = new StringBuilder();
            N = new StringBuilder();
            O = new StringBuilder();

            gindex = 0;
            for (String retval : users.get(rnd.nextInt(9)).split(" ")) {
                if(gindex == 0)
                    F.append(retval);
                else if(gindex == 1)
                    N.append(retval);
                else if(gindex == 2)
                    O.append(retval);
                gindex++;
            }

            Date date = new Date();
            contentValues.put(DBHelper.KEY_NF, F.toString());
            contentValues.put(DBHelper.KEY_NI, N.toString());
            contentValues.put(DBHelper.KEY_NO, O.toString());
            contentValues.put(DBHelper.KEY_DATE, dateFormat.format(date));
            database.insert(DBHelper.TABLE_USERS, null, contentValues);
        }

        database.close();
    }

    public void editLastUser()
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.execSQL("UPDATE users SET fam = " + "'Иванов', " +
                                           "name = " + "'Иван', " +
                                           "otch = " + "'Иванович' WHERE _id = (SELECT MAX(_id) FROM users)");
        database.close();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bListUsers:
                Intent i = new Intent(MainActivity.this, ListUsers.class);
                startActivity(i);
                break;
            case R.id.bAddUser:
                addUser(1);
                break;
            case R.id.bEditLastUser:
                editLastUser();
                break;
        }
    }
}
