package tr.edu.yildiz.selin_16011068;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UProperty;

public class sqliteKatmani extends SQLiteOpenHelper {

    private final String COLUMN_USER_EMAIL="email";
    private final String COLUMN_USER_ID="id";
    private final String COLUMN_USER_PASSWORD="sifre";
    private final String TABLE_USER="Kullanici";

    public sqliteKatmani(Context c){
        super(c,"Database3",null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql2 ="CREATE TABLE Sorular(id integer PRIMARY KEY AUTOINCREMENT, soru text ,dogruCevap text, yanlisCevap1 text,yanlisCevap2 text, yanlisCevap3 text,yanlisCevap4 text,kullaniciId integer) ";
        String sql ="CREATE TABLE Kullanici(id integer PRIMARY KEY AUTOINCREMENT, ad text, soyad text,email text, telNo text, sifre text ) ";


        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Kullanici");
        db.execSQL("drop table if exists Sorular");
    }


    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        //db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public boolean checkEmail(String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        //db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    public int getUserId (String email){
        int userId = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {
                COLUMN_USER_ID
        };
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();
        userId = cursor.getInt(0);
        cursor.close();
       // db.close();

        if (cursorCount > 0){
            return userId;
        }
        return userId;
    }

}
