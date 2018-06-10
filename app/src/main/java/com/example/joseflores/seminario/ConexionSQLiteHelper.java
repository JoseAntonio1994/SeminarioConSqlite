package com.example.joseflores.seminario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DDL.CREAR_TABLA_EXPOSITOR);
        db.execSQL(DDL.CREAR_TABLA_PRESENTACION);
        db.execSQL(DDL.CREAR_TABLA_ESTILO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DDL.TABLA_EXPOSITOR);
        db.execSQL("DROP TABLE IF EXISTS "+ DDL.TABLA_PRESENTACION);
        db.execSQL("DROP TABLE IF EXISTS "+ DDL.TABLA_ESTILO);
        onCreate(db);

    }
}
