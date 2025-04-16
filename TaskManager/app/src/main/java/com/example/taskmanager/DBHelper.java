package com.example.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "taskdata.db";
    private static  final int VERSION = 1;
    private static final String TABLE = "tasks";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "Create Table "+TABLE+" ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, due_date TEXT, priority TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE);
    }

    public boolean InsertTask(String name, String Date, String priority){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("due_date",Date);
        contentValues.put("priority",priority);
        long result = db.insert(TABLE,null,contentValues);
        if(result == -1){
            return false;
        }
        return true;
    }

    public Cursor getAllTasks(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * From "+ TABLE,null);
    }

    public boolean deleteTask(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * From "+TABLE+" where id=?", new String[]{String.valueOf(id)});
        if(cursor.getCount()>0){
            long result = db.delete(TABLE,"id=?",new String[]{String.valueOf(id)});
            return result != -1;
        }
        return false;
    }
}
