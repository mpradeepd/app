package com.myapp.easypark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseLite extends SQLiteOpenHelper {

    public static final String Database_name = "EasyDatabase";

    public static final String Table_name_1 = "Users";
    public static final String col_1_1 = "ID";
    public static final String col_1_2 = "EMAIL";
    public static final String col_1_3 = "PASSWORD";
    public static final String col_1_4 = "IsLogged";

    public static final String Table_name_2 = "Slot_data";
    public static final String col_2_1 = "ID";
    public static final String col_2_2 = "SlotNumber";
    public static final String col_2_3 = "IsReserved";
    public static final String col_2_4 = "StartTIme";
    public static final String col_2_5 = "EndTime";



    public DatabaseLite(@Nullable Context context){
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Users (ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT,PASSWORD TEXT,IsLogged INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name_1);
        onCreate(db);
    }

    public boolean insertUser(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1_2,email);
        contentValues.put(col_1_3,password);

        long result = db.insert(Table_name_1,null,contentValues);

        if (result==1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+Table_name_1,null);
        return result;
    }

    public boolean updateSigned(String email,String isLogged){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1_4,isLogged);
        db.update(Table_name_1,contentValues,"EMAIL = ?",new String[] {email});
        return true;
    }
}
