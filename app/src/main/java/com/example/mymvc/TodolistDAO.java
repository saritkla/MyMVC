package com.example.mymvc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.example.mymvc.DBHelper.COLUMN_PASSWORD;
import static com.example.mymvc.DBHelper.COLUMN_USERNAME;
import static com.example.mymvc.DBHelper.TABLE_NAME;

public class TodolistDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public TodolistDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public  void close(){
        dbHelper.close();
    }

    public ArrayList<String> getAllTodoList(){
            ArrayList<String> todoList = new ArrayList<String>();
            Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_NAME + ";",null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                todoList.add(cursor.getString(1));
                cursor.moveToNext();
            }
            cursor.close();
        return todoList;
    }

    public boolean insertDataUser(String username  ,String password){
        ArrayList<String> user = getAllTodoList();
        if (!user.isEmpty()) {
            Object[] mStringArray = user.toArray();
            for(int i = 0; i < mStringArray.length ; i++) {
                Log.d("string is", (String) mStringArray[i]);
                if (mStringArray[i].equals(username)) return false;
            }
        }
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = database.insert(TABLE_NAME, null,contentValues);
        return  result != -1;
    }
}
