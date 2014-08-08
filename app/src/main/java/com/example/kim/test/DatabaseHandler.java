package com.example.kim.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim on 8/8/2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "receiptManager";

    private static final String TABLE_RECEIPT = "receipt";

    private static final String KEY_ID = "id";
    private static final String KEY_DATE ="Date";
    private static final String KEY_ABN = "ABN";
    private static final String KEY_TOTAL = "Total";
    private static final String KEY_TAX = "Tax";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORY_TABLE = "RECEIPT TABLE " + TABLE_RECEIPT + "(" + KEY_ID
                + "INTEGER PRIMARY KEY, " + KEY_ABN + " TEXT," + KEY_DATE + " TEXT," + KEY_TOTAL + " TEXT," + KEY_TAX + " TEXT" + ")";
        db.execSQL(CREATE_CATEGORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT);

        onCreate(db);
    }

    public void addReceipt(Receipt receipt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ABN, receipt.getABN());
        values.put(KEY_DATE, receipt.getDate());
        values.put(KEY_TOTAL, receipt.getTotal());
        values.put(KEY_TAX,receipt.getTax());

        db.insert(TABLE_RECEIPT, null, values);
        db.close();
    }

    public Receipt getReceipt(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECEIPT, new String[] {KEY_ID, KEY_ABN, KEY_DATE,
                KEY_TOTAL, KEY_TAX}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null,
                null, null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Receipt receipt = new Receipt(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2),
                Float.parseFloat(cursor.getString(3)),
                Float.parseFloat(cursor.getString(4)));

        return receipt;
    }

    public List<Receipt> getAllReceipts() {
        List<Receipt> receiptList = new ArrayList<Receipt>();

        String selectQuery = "SELECT * FROM " + TABLE_RECEIPT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Receipt receipt = new Receipt();
                receipt.setID(Integer.parseInt(cursor.getString(0)));
                receipt.setABN(Integer.parseInt(cursor.getString(1)));
                receipt.setDate(cursor.getString(2));
                receipt.setTotal(Float.parseFloat(cursor.getString(3)));
                receipt.setTax(Float.parseFloat(cursor.getString(4)));

                receiptList.add(receipt);
            } while ( cursor.moveToNext());
        }

        return receiptList;
    }

    public int getReceiptCount() {
        String countQuery = "SELECT * FROM " + TABLE_RECEIPT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateReceipt(Receipt receipt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ABN, receipt.getABN());
        values.put(KEY_DATE, receipt.getDate());
        values.put(KEY_TOTAL, receipt.getTotal());
        values.put(KEY_TAX,receipt.getTax());

        return db.update(TABLE_RECEIPT, values, KEY_ID + "=?", new String[] { String.valueOf(receipt.getID()) });
    }

}
