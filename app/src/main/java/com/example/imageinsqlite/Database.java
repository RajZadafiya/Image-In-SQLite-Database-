package com.example.imageinsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context,"name.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tableimage(names text,image blob);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists tableimage");

    }

    public boolean insertdata(String name, byte[] img){
        SQLiteDatabase mydb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("names",name);
        contentValues.put("image", img);
        long insert = mydb.insert("tableimage",null,contentValues);
        if (insert == 1){
            return true;
        }else{
            return false;
        }
    }

    public String getname(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * From tableimage where names = ?",new String[]{name});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public Bitmap getImage(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * From tableimage where names = ?",new String[]{name});
        cursor.moveToFirst();
        byte[] bitmap = cursor.getBlob(0);
        Bitmap image = BitmapFactory.decodeByteArray(bitmap,0,bitmap.length);
        return image;
    }
}
