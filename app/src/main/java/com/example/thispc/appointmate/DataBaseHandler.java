package com.example.thispc.appointmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this pc on 15-03-2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATBASE_NAME = "Details";
    private static final String TABLE_USERS = "Users";

    private static final String key_id = "id";
    private static final String key_name = "name";
    private static final String key_username = "useranme";
    private static final String key_password = "password";

    public DataBaseHandler(Context context) {
        super(context, DATBASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_users = "CREATE TABLE " + TABLE_USERS + "(" + key_id + " INTEGER PRIMARY KEY," + key_name + " TEXT," + key_username + " TEXT," + key_password + " TEXT)";
        db.execSQL(create_table_users);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(UserDetails ud) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(key_name, ud.getName());
        values.put(key_username, ud.getUsername());
        values.put(key_password, ud.getPassword());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public UserDetails getuser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{key_id, key_name, key_username, key_password}, key_username + "=?", new String[]{username}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        UserDetails ud = new UserDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return ud;
    }

    public List<UserDetails> getAllUsers() {
        List<UserDetails> userList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                UserDetails ud = new UserDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                userList.add(ud);
            }while(cursor.moveToNext());
        }
        return userList;
    }

}
