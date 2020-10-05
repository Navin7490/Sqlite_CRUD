package com.example.sqlite_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABSE = "studentRecor.db";
    public static final String TABLE_NAME = "student";
    public static final String ID = "Id";
    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String MOBILE = "Mobile";
    public static final String GENDER = "Gender";
    public static final String IMAGE = "Image";


    public Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABSE, null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Email TEXT,Password TEXT,Mobile TEXT," +
                "Gender TEXT,Image BLOG)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertData(String name, String email, String password, String mobile, String gender,byte[]image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, password);
        contentValues.put(MOBILE, mobile);
        contentValues.put(GENDER, gender);
        contentValues.put(IMAGE, image);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void LoginData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE " + EMAIL + "=? AND " + PASSWORD + "=?", new String[]{email, password});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToNext();
                Toast.makeText(context.getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context.getApplicationContext(), "Incurrect Email or Password", Toast.LENGTH_SHORT).show();

            }
        }

    }
    public Cursor ViewAllData(){
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME,null);
    }
    public  Integer  DeleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
         return   db.delete(TABLE_NAME,"Id=?",new String[]{id});
    }

}

