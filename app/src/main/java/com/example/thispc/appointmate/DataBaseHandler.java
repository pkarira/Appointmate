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
    private static final String DATABASE_NAME = "Details";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_HOSPITALS = "Hospitals";
    private static final String TABLE_DOCTORS = "Doctors";

    private static final String key_user_id = "id";
    private static final String key_user_name = "name";
    private static final String key_user_username = "useranme";
    private static final String key_user_password = "password";

    private static final String key_hospital_id = "id";
    private static final String key_hospital_name = "name";
    private static final String key_hospital_address = "address";
    private static final String key_hospital_contact = "contact";
    private static final String key_hospital_rating = "rating";

    private static final String key_doctor_id = "id";
    private static final String key_doctor_name = "name";
    private static final String key_doctor_contact = "contact";
    private static final String key_doctor_qual = "qualification";
    private static final String key_doctor_type = "type";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_users = "CREATE TABLE " + TABLE_USERS + "(" + key_user_id + " INTEGER PRIMARY KEY," + key_user_name + " TEXT," + key_user_username + " TEXT," + key_user_password + " TEXT)";
        db.execSQL(create_table_users);
        String create_table_hospitals = "CREATE TABLE " + TABLE_HOSPITALS + "(" + key_hospital_id + " INTEGER PRIMARY KEY," + key_hospital_name + " TEXT," + key_hospital_address + " TEXT," + key_hospital_contact + " INTEGER," + key_hospital_rating + " INTEGER)";
        db.execSQL(create_table_hospitals);
        String create_table_doctors = "CREATE TABLE " + TABLE_DOCTORS + "(" + key_doctor_id + " INTEGER PRIMARY KEY," + key_doctor_name + " TEXT," + key_doctor_contact + " INTEGER," + key_doctor_qual + " TEXT," + key_doctor_type + " TEXT)";
        db.execSQL(create_table_doctors);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(UserDetails ud) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(key_user_name, ud.getName());
        values.put(key_user_username, ud.getUsername());
        values.put(key_user_password, ud.getPassword());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public UserDetails getuser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{key_user_id, key_user_name, key_user_username, key_user_password}, key_user_username + "=?", new String[]{username}, null, null, null, null);
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
        if (cursor.moveToFirst()) {
            do {
                UserDetails ud = new UserDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                userList.add(ud);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public void addHospital(HospitalDetails hd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(key_hospital_name, hd.getName());
        values.put(key_hospital_address, hd.getAddress());
        values.put(key_hospital_contact, hd.getContact());
        values.put(key_hospital_rating, hd.getRating());

        db.insert(TABLE_HOSPITALS, null, values);
        db.close();
    }

    public HospitalDetails getHospital(String hospitalName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DOCTORS, new String[]{key_hospital_id, key_hospital_name, key_hospital_address, key_hospital_contact, key_hospital_rating}, key_hospital_name + "=?", new String[]{hospitalName}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        HospitalDetails hd = new HospitalDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Long.parseLong(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
        return hd;
    }

    public List<HospitalDetails> getAllHospitals() {
        List<HospitalDetails> hospitalList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_HOSPITALS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                HospitalDetails hd = new HospitalDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Long.parseLong(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
                hospitalList.add(hd);
            } while (cursor.moveToNext());
        }
        return hospitalList;
    }

    public void addDoctor(DoctorDetails dd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(key_doctor_name, dd.getName());
        values.put(key_doctor_contact, dd.getContact());
        values.put(key_doctor_qual, dd.getQualification());
        values.put(key_doctor_type, dd.getType());

        db.insert(TABLE_DOCTORS, null, values);
        db.close();
    }

    public DoctorDetails getDoctor(String doctorName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DOCTORS, new String[]{key_doctor_id, key_doctor_name, key_doctor_contact, key_doctor_qual, key_doctor_type}, key_doctor_name + "=?", new String[]{doctorName}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        DoctorDetails dd = new DoctorDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Long.parseLong(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
        return dd;
    }

    public List<DoctorDetails> getAllDoctors() {
        List<DoctorDetails> doctorList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_DOCTORS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                DoctorDetails dd = new DoctorDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Long.parseLong(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
                doctorList.add(dd);
            } while (cursor.moveToNext());
        }
        return doctorList;
    }

    public List<DoctorDetails> getAllDoctors(String type){
        List<DoctorDetails> doctorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DOCTORS, new String[]{key_doctor_id, key_doctor_name, key_doctor_contact, key_doctor_qual, key_doctor_type}, key_doctor_type + "=?", new String[]{type}, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                DoctorDetails dd = new DoctorDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Long.parseLong(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
                doctorList.add(dd);
            } while (cursor.moveToNext());
        }
        return doctorList;
    }

}
