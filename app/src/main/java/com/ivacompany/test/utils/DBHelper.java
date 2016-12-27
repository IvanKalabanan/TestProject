package com.ivacompany.test.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME_TABLE = "articles";

    public DBHelper(Context context) {
        super(context, "list_articles", null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table folder ("
                + "id integer primary key autoincrement ,"
                + "p_id integer,"
                + "filename text,"
                + "modDate text,"
                + "is_orange integer,"
                + "is_blue integer" +
                ");");
        db.execSQL("create table file ("
                + "id integer primary key autoincrement ,"
                + "f_id integer,"
                + "filename text,"
                + "modDate text,"
                + "fileType text,"
                + "is_orange integer,"
                + "is_blue integer" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
