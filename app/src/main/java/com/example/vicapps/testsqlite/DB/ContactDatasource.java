package com.example.vicapps.testsqlite.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vicapps.testsqlite.Model.Contacto;

import java.util.ArrayList;

/**
 * Created by 21542295 on 06/02/2017.
 */
public class ContactDatasource {

    private Context mContext;
    private SQLHelper mSQLiteHelper ;
    public ContactDatasource(Context context) {
        mContext = context;
        mSQLiteHelper = new SQLHelper (mContext);
    }

    public SQLiteDatabase openReadable() {
        return mSQLiteHelper.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return mSQLiteHelper.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }

    public void insertContact(Contacto contacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLHelper.COLUMN_NAME, contacto.getName());
        contentValues.put(SQLHelper.COLUMN_MAIL, contacto.getEmail());

        database.insert(SQLHelper.TABLE_NAME,null,contentValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    /*
    public void insertContact(Contacto contacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        database.execSQL("INSERT INTO " +
        + ContactEntry.TABLE_NAME+
        "VALUES " +
        "(null,'"+contacto.getName()+
        "','"+contacto.getEmail+"'));
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
}
     */


    //---borrar un contacto en la base de datos por id---
    public void borrarContacto(long rowId) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        database.delete(SQLHelper.TABLE_NAME,
                //convierte un string en un entero
                String.format("%s=%d",
                         SQLHelper.COLUMN_ID, rowId),
                null);
        database.setTransactionSuccessful();
        database.endTransaction();

        close(database);
    }

    public void  borrarContact(long rowid){
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        database.execSQL("DELETE FROM "+mSQLiteHelper.TABLE_NAME + " WHERE "+mSQLiteHelper.COLUMN_ID + " = "+rowid+";");
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);

    }

    //--- actualizar un contacto en la base de datos ---
    public void actualizarContacto(Contacto contacto) {
        Log.i("ENTRA EN CONTACTO",contacto.getId()+"");
        Log.i("ENTRA EN CONTACTO",contacto.getName());
        Log.i("ENTRA EN CONTACTO",contacto.getEmail());
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLHelper.COLUMN_NAME,
                contacto.getName());
        contentValues.put(SQLHelper.COLUMN_MAIL,
                contacto.getEmail());
        database.update(SQLHelper.TABLE_NAME,
                contentValues,
                String.format("%s=%d", SQLHelper.COLUMN_ID,contacto.getId()),
                null);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
        Log.i("ENTRA EN CONTACTO","MODIFICA CONTACTO");
    }

    public void actualizarContact(Contacto contacto){

        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        database.execSQL("UPDATE "+SQLHelper.TABLE_NAME+" SET "+SQLHelper.COLUMN_NAME+ " = '"+contacto.getName()+
                "', "+SQLHelper.COLUMN_MAIL+" = '"+contacto.getEmail()+"' WHERE "+SQLHelper.COLUMN_ID+" = "+contacto.getId());
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);


    }

    private int getIntFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }
    private String getStringFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);
    }
    private Float getFloatFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getFloat(columnIndex);
    }
    //select * from contactos;
    public ArrayList<Contacto> readContacts() {
        SQLiteDatabase database = openReadable();
        Cursor cursor = database.query( SQLHelper.TABLE_NAME,
        new String[]{SQLHelper.COLUMN_ID,
                SQLHelper.COLUMN_NAME,
                SQLHelper.COLUMN_MAIL},
                null, null, null, null, null );
        ArrayList<Contacto> contactos= new ArrayList<Contacto>();
        if (cursor.moveToFirst()) {
            do {
                Contacto contacto = new Contacto(
                        getIntFromColumnName(cursor, SQLHelper.COLUMN_ID),
                        getStringFromColumnName(cursor, SQLHelper.COLUMN_NAME),
                        getStringFromColumnName(cursor,SQLHelper.COLUMN_MAIL)
                        );
                contactos.add(contacto );
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return contactos;
    }

    public Contacto readContact(long rowId) {
        SQLiteDatabase database = openReadable();
        Contacto contacto = new Contacto();
        Cursor cursor = database.rawQuery(
                "SELECT "+SQLHelper.COLUMN_ID+","+SQLHelper.COLUMN_NAME+","+SQLHelper.COLUMN_MAIL +
                        " FROM "+SQLHelper.TABLE_NAME +
                        " WHERE "+SQLHelper.COLUMN_ID+" = "+rowId+";",
                null);
        if (cursor.moveToFirst()) {
            contacto = new Contacto (
                    getIntFromColumnName(cursor, SQLHelper.COLUMN_ID),
                    getStringFromColumnName(cursor, SQLHelper.COLUMN_NAME),
                    getStringFromColumnName(cursor,SQLHelper.COLUMN_MAIL)
                    );
        }
        cursor.close();
        database.close();
        return contacto;
    }






}
