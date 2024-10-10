package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "STIDVO.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USER_TABLE = "user";
    private static final String USER_ID = "user_id";
    private static final String USER_USERNAME = "username";
    private static final String USER_PASSWORD = "password";

    private static final String SQL_CREATE_USERS =
            "CREATE TABLE " + USER_TABLE + " (" +
                    USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    USER_USERNAME + " TEXT," +
                    USER_PASSWORD + " TEXT)";

    private SQLiteDatabase db;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades if needed
    }

    public void updateUser(String studID, String updatedUsername, String updatedPassword) {
        ContentValues values = new ContentValues();
        values.put(USER_USERNAME, updatedUsername);
        values.put(USER_PASSWORD, updatedPassword);

        int record = db.update(USER_TABLE, values, USER_ID + " = ?", new String[]{studID});
        Log.d("UPDATE_QUERY", "Record Updated: " + record);
    }

    public long insertUser(Student student) {
        ContentValues values = new ContentValues();
        values.put(USER_USERNAME, student.getUsername());
        values.put(USER_PASSWORD, student.getPassword());

        return db.insert(USER_TABLE, null, values);
    }

    public List<Student> retrieveUsers() {
        List<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE, null);
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(USER_ID));
                String username = cursor.getString(cursor.getColumnIndex(USER_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
                Student student = new Student(id, username, password);
                students.add(student);
            }
        } finally {
            cursor.close();
        }
        return students;
    }
}
