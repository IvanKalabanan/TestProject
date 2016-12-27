package com.ivacompany.test.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ivacompany.test.DBHelper;
import com.ivacompany.test.TestApp;
import com.ivacompany.test.model.FileModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 27.12.16.
 */

public class Utils {

    private static List<FileModel> list;
    private static DBHelper dbHelper;
    private static SQLiteDatabase db;
    private static DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);

    public static void initDB(){
        if (dbHelper == null){
            dbHelper = new DBHelper(TestApp.getAppContext());
            db = dbHelper.getWritableDatabase();
        }
    }

    public static List<FileModel> getFiles(int parentId){
        list = new ArrayList<>();
        readFiles(parentId);
        readFolders(parentId);
        return list;
    }

    private static void readFiles(int parentId) {
        String selectQuery = "SELECT id,f_id,filename,modDate,fileType,is_orange,is_blue" +
                " FROM file WHERE f_id=" + parentId;
        Cursor c = db.rawQuery(selectQuery, new String[] {});

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("id");
            int f_id = c.getColumnIndex("f_id");
            int filename = c.getColumnIndex("filename");
            int modDate = c.getColumnIndex("modDate");
            int fileType = c.getColumnIndex("fileType");
            int is_orange = c.getColumnIndex("is_orange");
            int is_blue = c.getColumnIndex("is_blue");

            do {
                try {
                    list.add(new FileModel(
                            c.getInt(id),
                            c.getInt(f_id),
                            c.getString(filename),
                            false,
                            formatter.parse(c.getString(modDate)),
                            c.getString(fileType),
                            c.getInt(is_orange),
                            c.getInt(is_blue)
                    ));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (c.moveToNext());
        } else
            Log.d("SQLITE", "0 rows");
        c.close();
    }

    private static void readFolders(int parentId) {
        String selectQuery = "SELECT id,p_id,filename,modDate,is_orange,is_blue" +
                " FROM folder WHERE p_id=" + parentId;
        Cursor c = db.rawQuery(selectQuery, new String[] {});

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("id");
            int p_id = c.getColumnIndex("p_id");
            int filename = c.getColumnIndex("filename");
            int modDate = c.getColumnIndex("modDate");
            int is_orange = c.getColumnIndex("is_orange");
            int is_blue = c.getColumnIndex("is_blue");

            do {
                try {
                    list.add(new FileModel(
                            c.getInt(id),
                            c.getInt(p_id),
                            c.getString(filename),
                            true,
                            formatter.parse(c.getString(modDate)),
                            "",
                            c.getInt(is_orange),
                            c.getInt(is_blue)
                    ));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            Log.d("SQLITE", "0 rows");
        c.close();
    }

}
