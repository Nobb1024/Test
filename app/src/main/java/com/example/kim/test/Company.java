package com.example.kim.test;

/**
 * Created by Kim on 8/11/2014.
 */
public class Company {
    int _abn;
    String _name;

    public Company() {}

    public Company(int abn, String name) {
        this._abn = abn;
        this._name = name;
    }

    public int get_abn() { return this._abn; }

    public void set_abn(int abn) { this._abn = abn; }

    public String get_name() { return this._name; }

    public void set_name(String name) { this._name = name; }
}
