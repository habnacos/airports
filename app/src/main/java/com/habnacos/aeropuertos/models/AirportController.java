package com.habnacos.aeropuertos.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class AirportController {
    private DBModel db;
    private Context c;

    public AirportController(Context c) {
        this.db = new DBModel(c,1);
        this.c = c;
    }

    public long create(Airport a) {
        try {
            SQLiteDatabase sql = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DB.col_code, a.getCode());
            values.put(DB.col_name, a.getName());
            values.put(DB.col_country, a.getCountry());
            values.put(DB.col_city, a.getCity());
            values.put(DB.col_address, a.getAddress());
            values.put(DB.col_latitude, a.getLatitude());
            values.put(DB.col_length, a.getLength());

            return sql.insert(DB.table_name, null, values);
        } catch (Exception ex) {
            Toast.makeText(c, "Ocurrió un error " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return 0;
        }
    }

    public Cursor getByCode(String code) {
        try {
            SQLiteDatabase sql = db.getReadableDatabase();
            Cursor cursor = sql.rawQuery("SELECT * FROM "+DB.table_name+" WHERE "+DB.col_code+" = '"+code+"';", null);
            if (cursor != null)
                cursor.moveToFirst();
            return cursor;
        } catch (Exception ex) {
            Toast.makeText(c, "Ocurrió un error",Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public boolean updateByCode (String code, Airport a) {
        try {
            SQLiteDatabase sql = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DB.col_code, a.getCode());
            values.put(DB.col_name, a.getName());
            values.put(DB.col_country, a.getCountry());
            values.put(DB.col_city, a.getCity());
            values.put(DB.col_address, a.getAddress());
            values.put(DB.col_latitude, a.getLatitude());
            values.put(DB.col_length, a.getLength());

            return sql.update(DB.table_name, values, DB.col_code + "= '" + code+"'", null) > 0;
        } catch (Exception ex) {
            Toast.makeText(c, "Ocurrió un error" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public Cursor getAll() {
        try {
            SQLiteDatabase sql = db.getReadableDatabase();
            return sql.rawQuery("select * from "+DB.table_name+" order by " + DB.col_code, null);
        } catch (Exception ex){
            Toast.makeText(c, "Ocurrió un error " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public boolean deleteByCode(String code) {
        SQLiteDatabase sql = db.getWritableDatabase();
        return sql.delete(DB.table_name, DB.col_code + "=" + code, null) > 0;
    }
}
