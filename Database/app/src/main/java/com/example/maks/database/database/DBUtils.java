package com.example.maks.database.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.example.maks.database.App;
import com.example.maks.database.models.User;

public class DBUtils {
    private Cursor cursor;

    public User getUserIdFull(long id){
        User temp=new User();
        String[] columns=new String[]{"id","name","surname","address","email","phone"};
        String selection="id==?";
        String[] selectionArgs=new String[]{String.valueOf(id)};
        cursor= App.getInstance().getDb().query("user",columns,selection,selectionArgs,null,null,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idColIndex = cursor.getColumnIndex("id");
                int nameColIndex = cursor.getColumnIndex("name");
                int surnameColIndex = cursor.getColumnIndex("surname");
                int addressColIndex=cursor.getColumnIndex("address");
                int emailColIndex = cursor.getColumnIndex("email");
                int phoneColIndex=cursor.getColumnIndex("phone");
                temp=new User(cursor.getLong(idColIndex),cursor.getString(nameColIndex),cursor.getString(surnameColIndex),cursor.getString(addressColIndex),
                        cursor.getString(emailColIndex),cursor.getString(phoneColIndex));
            }
        } else {

        }
        cursor.close();
        return temp;
    }
    public void deleteUser(long userId){

        SQLiteStatement statement = App.getInstance().getDb().compileStatement("DELETE FROM user WHERE id == ?");
        statement.bindString(1, String.valueOf(userId));
        try {
            statement.execute();
        } finally {
            statement.close();
        }

    }

    public void addUser(String name,String surname,String address,String email,String phone){

        try {
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("surname", surname);
            cv.put("address", address);
            cv.put("email", email);
            cv.put("phone", phone);
            App.getInstance().getDb().insert("user", null, cv);
        } finally {

        }


    }

}
