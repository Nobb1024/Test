package com.example.kim.test.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kim.test.Receipt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim on 8/8/2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "receiptManager";
    //General Column Name
    private static final String KEY_ID = "Id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_DESCRIPTION = "Description";
    //Receipt Table
    private static final String TABLE_RECEIPT = "Receipt";
    private static final String KEY_DATE ="Date";
    private static final String KEY_ABN = "ABN";
    private static final String KEY_TOTAL = "Total";
    private static final String KEY_TAX = "GST";
    private static final String KEY_CATALOG_ID = "CatalogId";
    private static final String KEY_CLAIMABLE = "Claimable";
    //Category Table
    private static final String TABLE_CATEGORY = "Category";
    private static final String KEY_USER_ID = "UserId";
    private static final String KEY_CATEGORY_ID = "CategoryId";
    //Company Table
    private static final String TABLE_COMPANY = "Company";
    //User Table
    private static final String TABLE_USER = "User";
    private static final String KEY_FIRST_NAME = "FirstName";
    private static final String KEY_LAST_NAME = "LastName";
    //Catalog Table
    private static final String TABLE_CATALOG = "Catalog";
    //ReceiptImage Table
    private static final String TABLE_RECEIPT_IMAGE = "ReceiptImage";
    private static final String KEY_FILE_PATH = "FilePath";
    private static final String KEY_FILE_NAME = "FileName";
    private static final String KEY_RECEIPT_ID = "ReceiptId";
    //Create Statement
    private static final String CREATE_RECEIPT_TABLE =
            "CREATE TABLE " + TABLE_RECEIPT
            + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY ("
            + KEY_CATALOG_ID + ") REFERENCES" + TABLE_CATALOG + " (" + KEY_ID + "), "
            + KEY_ABN + " INTEGER," + KEY_DATE + " TEXT," + KEY_TOTAL + " INTEGER,"
            + KEY_TAX + " INTEGER," + KEY_CLAIMABLE + "INTEGER" + ")";

    private static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE" + TABLE_CATEGORY
            + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY ("
            + KEY_USER_ID + ") REFERENCES " + TABLE_RECEIPT + " (" + KEY_ID + ")"
            + KEY_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT" + ")";

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_USER
            + "(" + KEY_ID + "INTEGER PRIMARY KEY, " + KEY_FIRST_NAME + " TEXT,"
            + KEY_LAST_NAME + " TEXT" + ")";

    private static final String CREATE_RECEIPT_IMAGE =
            "CREATE TABLE " + TABLE_RECEIPT_IMAGE
            + "(" +KEY_ID + "INTEGER PRIMARY KEY, FOREIGN KEY (" + KEY_RECEIPT_ID
            + ") REFERENCES " + TABLE_RECEIPT + " (" + KEY_ID + "), " + KEY_FILE_PATH
            + " TEXT, " + KEY_FILE_NAME + " TEXT" + ")";

    private static final String CREATE_CATALOG =
            "CREATE TABLE " + TABLE_CATALOG
            + "(" + KEY_ID + "INTEGER PRIMARY KEY, FOREIGN KEY (" + KEY_USER_ID

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECEIPT_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_COMPANY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT_CATEGORY);
        onCreate(db);
    }

    public void addReceipt(Receipt receipt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ABN, receipt.get_abn());
        values.put(KEY_DATE, String.valueOf(receipt.get_date()));
        values.put(KEY_TOTAL, String.valueOf(receipt.getTotal()));
        values.put(KEY_TAX, String.valueOf(receipt.getTax()));

        db.insert(TABLE_RECEIPT, null, values);
        db.close();
    }

    public Receipt getReceipt(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

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
