package bbitga.strathmore.com.sqlitelaba;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nba on 19/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contactsManager";

    private static final String TABLE_CONTACTS = "contacts";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    private static final String TABLE_UNIT = "unit";

    private static final String KEY_UID = "uid";
    private static final String KEY_UNAME = "uname";
    private static final String KEY_UCODE = "ucode";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE ="CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);


        String CREATE_UNIT_TABLE ="CREATE TABLE " + TABLE_UNIT + "("
                + KEY_UID + " INTEGER PRIMARY KEY," + KEY_UNAME + " TEXT,"
                + KEY_UCODE + " TEXT" + ")";
        db.execSQL(CREATE_UNIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT);
        onCreate(db);

    }



    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_PH_NO, contact.get_phone_number());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public void addUnit(Unit unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UNAME, unit.get_uname());
        values.put(KEY_UCODE, unit.get_ucode());

        db.insert(TABLE_UNIT, null, values);
        db.close();
    }


    public Contact getContacts (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return contact;
    }
    public Unit getUnit (int uid) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_UNIT, new String[]{KEY_UID,
                        KEY_UNAME, KEY_UCODE}, KEY_UID + "=?",
                new String[]{String.valueOf(uid)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Unit unit = new Unit(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return unit;


    }
        // Getting All Contacts
        public List<Contact> getAllContacts() {
            List<Contact> contactsList = new ArrayList<>();
            //select All query
            String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            //looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact();
                    contact.set_id(Integer.parseInt(cursor.getString(0)));
                    contact.set_name(cursor.getString(1));
                    contact.set_phone_number(cursor.getString(2));
                    //Adding contact to List
                    contactsList.add(contact);
                } while (cursor.moveToNext());
            }

            //return contact list
            return contactsList;
        }

    // Getting All unit
    public List<Unit> getAllUnit() {
        List<Unit> unitList = new ArrayList<>();
        //select All query
        String selectQuery = "SELECT  * FROM " + TABLE_UNIT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Unit unit = new Unit();
                unit.set_uid(Integer.parseInt(cursor.getString(0)));
                unit.set_uname(cursor.getString(1));
                unit.set_ucode(cursor.getString(2));
                //Adding contact to List
                unitList.add(unit);
            } while (cursor.moveToNext());
        }

        //return contact list
        return unitList;
    }

            //Getting contacts count
            public int getContactsCount(){
            String countQuery;
                countQuery = "SELECT * FROM " +TABLE_CONTACTS;
                SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery,null);
            cursor.close();

            //return count
            return cursor.getCount();
        }

    //Getting unit count
    public int getUnitCount(){
        String countQuery;
        countQuery = "SELECT * FROM " +TABLE_UNIT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        //return count
        return cursor.getCount();
    }


        public int updateContacts(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, contact.get_name() );
            values.put(KEY_PH_NO, contact.get_phone_number());

            return  db.update(TABLE_CONTACTS, values,KEY_ID + "= ?",
                    new String [] {String.valueOf(contact.get_id())});
        }

    public int updateUnit(Unit unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UNAME, unit.get_uname() );
        values.put(KEY_UCODE, unit.get_ucode());

        return  db.update(TABLE_UNIT, values,KEY_UID + "= ?",
                new String [] {String.valueOf(unit.get_uid())});
    }


        public void deleteContacts(Contact contact){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_CONTACTS,KEY_ID + " = ?",
                    new String[] {String.valueOf(contact.get_id())});
            db.close();
        }

    public void deleteUnit(Unit unit){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_UNIT,KEY_UID + " = ?",
                new String[] {String.valueOf(unit.get_uid())});
        db.close();
    }



        }

