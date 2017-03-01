package com.example.vicapps.testsqlite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by 21542295 on 06/02/2017.
 */
public class SQLHelper extends SQLiteOpenHelper{
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MAIL = "email";
    public static final String TABLE_NAME = "CONTACTS";
    static final String DATABASE_NAME ="ContactDB";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE_CONTACT =
            "CREATE TABLE "+TABLE_NAME+"( "+
                    COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    COLUMN_NAME+" TEXT NOT NULL ,"+
                    COLUMN_MAIL+" TEXT NOT NULL);";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //se creara por primera vez cuando se instale en el movil , cuando se ejecute la primera vez , si no existe la creara
        db.execSQL(CREATE_TABLE_CONTACT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
