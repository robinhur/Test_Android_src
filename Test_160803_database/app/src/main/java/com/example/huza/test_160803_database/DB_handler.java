package com.example.huza.test_160803_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HuZA on 2016-08-03.
 */
public class DB_handler extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "product.db";
    static final String TABLE_PRODUCTS = "products";

    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_QUANTITY = "quantity";

    public DB_handler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCTS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
                + COLUMN_QUANTITY + " INTEGER)";
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Product p) {
        ContentValues value = new ContentValues();
        value.put(COLUMN_NAME, p.getName());
        value.put(COLUMN_QUANTITY, p.getQuantity());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, value);
        db.close();
    }

    public Product findProduct(String find_name) {
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_NAME + "= \"" + find_name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Product found = new Product();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            found.setId(Integer.parseInt(cursor.getString(0)));
            found.setName(cursor.getString(1));
            found.setQuantity(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            found = null;
        }

        db.close();
        return found;
    }

    public boolean deleteProduct(String delete_name) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_NAME + "= \"" + delete_name + "\"";

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[]{String.valueOf(Integer.parseInt(cursor.getString(0)))});
            cursor.close();
            result = true;
        }

        return result;
    }

}
