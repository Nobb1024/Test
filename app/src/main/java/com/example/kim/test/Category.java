package com.example.kim.test;

/**
 * Created by Kim on 8/11/2014.
 */
public class Category {
    int _id;
    String _name;
    int _userId;

    public Category() {}

    public Category(int id, String name, int userId) {
        this._id = id;
        this._name = name;
        this._userId = userId;
    }

    public int get_id() { return this._id; }

    public void set_id(int id) { this._id = id; }

    public int get_userId() { return this._userId; }

    public void set_userId(int userId) { this._userId = userId; }

    public String get_name() { return this._name; }

    public void set_name(String name) { this._name = name; }
}
