package com.example.maks.database;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.maks.database.database.DBHelper;


public class App extends Application {
    private static App instance;
    private DBHelper dbOpenHelper;
    private SQLiteDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dbOpenHelper = new DBHelper(this);
        db = dbOpenHelper.getWritableDatabase();
    }
    public static App getInstance() {
        return App.instance;
    }
    public SQLiteDatabase getDb() {
        return db;
    }
}