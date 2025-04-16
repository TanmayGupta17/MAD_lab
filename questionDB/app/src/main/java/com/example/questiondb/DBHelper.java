package com.example.questiondb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT, email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Userdetail");
    }

    public boolean insertUserDetails(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("Userdetails",null,contentValues);
        if(result == -1) return false;
        return true;
    }
    public boolean UpdateUserDetails(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("password",password);
        Cursor cursor = db.rawQuery("Select * From Userdetails where email=?", new String[]{email});
        if(cursor.getCount() > 0){
            long result = db.update("Userdetails",contentValues,"email=?",new String[]{email});
            if(result == -1) return false;
            return true;
        }
        return  false;
        
    }

    public boolean deleteUserDetails(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * From Userdetails where email=?", new String[]{email});
        if(cursor.getCount() > 0){
            long result = db.delete("Userdetails","email=?",new String[]{email});
            if(result == -1) return false;
            return true;
        }
        return  false;

    }

    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * From Userdetails",null);
        return cursor;
    }
}
