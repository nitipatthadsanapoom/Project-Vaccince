package com.thadsanapoom.nitipat.googlelogin.io;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.thadsanapoom.nitipat.googlelogin.io.model.Person;

import java.util.ArrayList;

/**
 * Created by Nitipat on 20/3/2559.
 */
public class DBHandler  extends SQLiteOpenHelper {

        // static variables

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "person.db";

        // Contacts table name
        public static final String TABLE_PERSON = "person";

        // Contacts Table Columns names
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_LASTNAME = "lastname";
        private static final String COLUMN_BIRTHDAY = "date";
        private static final String COLUMN_GENDER = "gender";
        private static final String COLUMN_WEIGHT = "weight";
        private static final String COLUMN_LENGHT = "lenght";
        private static final String COLUMN_BLOODTYPE = "bloodtype";
        private static final String COLUMN_ALLERGIC = "allergic";

        private final ArrayList<Person> personList = new ArrayList<Person>();

        //constructor
        public DBHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }




        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PERSON + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_LASTNAME + " TEXT,"
                    + COLUMN_BIRTHDAY + " TEXT,"
                    + COLUMN_GENDER + " TEXT,"
                    + COLUMN_WEIGHT + " TEXT,"
                    + COLUMN_LENGHT + " TEXT,"
                    + COLUMN_BLOODTYPE + " TEXT,"
                    + COLUMN_ALLERGIC + " TEXT"+")";
            db.execSQL(CREATE_CONTACTS_TABLE);

        }

        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);

            // Create tables again
            onCreate(db);
        }

        /**
         * All CRUD(Create, Read, Update, Delete) Operations
         */

        // Adding new Person
        public void addPerson(Person person) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, person.getName()); // Person Name
            values.put(COLUMN_LASTNAME, person.getLastName());
            values.put(COLUMN_BIRTHDAY, person.getBirthDay());
            values.put(COLUMN_GENDER, person.getGender());
            values.put(COLUMN_WEIGHT, person.getWeight());
            values.put(COLUMN_LENGHT, person.getLenght());
            values.put(COLUMN_BLOODTYPE, person.getBloodType());
            values.put(COLUMN_ALLERGIC, person.getAllergic());
            // Inserting Row
            db.insert(TABLE_PERSON, null, values);
            db.close(); // Closing database connection
        }

        // Getting single contact
        public Person getPerson(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_PERSON, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_BIRTHDAY, COLUMN_GENDER
                    , COLUMN_WEIGHT, COLUMN_LENGHT, COLUMN_BLOODTYPE, COLUMN_ALLERGIC }, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Person person = new Person(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2)
                    , cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
            // return contact
            cursor.close();
            db.close();

            return person;
        }

        // Getting All Person
        public ArrayList<Person> getPerson() {
            try {
                personList.clear();

                // Select All Query
                String selectQuery = "SELECT  * FROM " + TABLE_PERSON;

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Person person = new Person();
                        person.setId(Integer.parseInt(cursor.getString(0)));
                        person.setName(cursor.getString(1));
                        person.setLastName(cursor.getString(2));
                        person.setBirthDay(cursor.getString(3));
                        person.setGender(cursor.getString(4));
                        person.setWeigth(cursor.getString(5));
                        person.setLenght(cursor.getString(6));
                        person.setBloodType(cursor.getString(7));
                        person.setAllergic(cursor.getString(8));

                        // Adding contact to list
                        personList.add(person);
                    } while (cursor.moveToNext());
                }

                // return contact list
                cursor.close();
                db.close();
                return personList;
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("all_contact", "" + e);
            }

            return personList;
        }

        // Updating single person
        public int updatePerson(Person person) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, person.getId());
            values.put(COLUMN_NAME, person.getName());
            values.put(COLUMN_LASTNAME, person.getLastName());
            values.put(COLUMN_BIRTHDAY, person.getBirthDay());
            values.put(COLUMN_GENDER, person.getGender());
            values.put(COLUMN_WEIGHT, person.getWeight());
            values.put(COLUMN_LENGHT, person.getLenght());
            values.put(COLUMN_BLOODTYPE, person.getBloodType());
            values.put(COLUMN_ALLERGIC, person.getAllergic());




            // updating row
            return db.update(TABLE_PERSON, values, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(person.getId())});
        }

        // Deleting single contact
        public void deleteContact(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_PERSON, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)});
            db.close();
        }
    }



