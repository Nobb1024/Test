package com.example.kim.test;

import android.database.Cursor;

import java.util.Currency;
import java.util.Date;

/**
 * Created by Kim on 8/8/2014.
 */
public class Receipt {
    int _id;
    int _abn;
    Date _date;
    Currency _total;
    Currency _tax;

    public Receipt() {}

    public Receipt(int id, int abn, Date date, Currency total, Currency tax) {
        this._id = id;
        this._abn = abn;
        this._date = date;
        this._total = total;
        this._tax = tax;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public int get_abn(){
        return this._abn;
    }

    public void set_abn(int abn){
        this._abn = abn;
    }

    public Date get_date(){
        return this._date;
    }

    public void set_date(Date date){
        this._date = date;
    }

    public Currency get_total(){
        return this._total;
    }

    public void set_total(Currency total){
        this._total = total;
    }

    public Currency get_tax(){
        return this._tax;
    }

    public void set_tax(Currency tax){
        this._tax = tax;
    }
}
