package com.bruno.lista_compras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "Applistacompras";

    public Banco(Context contexto){
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "CREATE TABLE  lista ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                " nomelista TEXT);" );


        sqLiteDatabase.execSQL("CREATE TABLE  produto(" +
                "idproduto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nomeproduto TEXT," +
                "tipoproduto TEXT," +
                "quantidade TEXT,"+
                "idlista INTEGER," +
                "FOREIGN KEY (idlista) REFERENCES lista(id));");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

