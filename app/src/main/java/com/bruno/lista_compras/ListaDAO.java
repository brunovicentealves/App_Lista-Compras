package com.bruno.lista_compras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListaDAO {


    public static void inserir(Context contexto, Lista lista){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nomelista", lista.getNomelista() );


        db.insert("lista" , null , valores );

    }


    public static void editar(Context contexto, Lista lista){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nomelista", lista.getNomelista() );
        valores.put( "id", lista.getId() );

        db.update("lista", valores,
                " id = " + lista.getId(), null);

    }


    public static void excluir(Context contexto, int idProduto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("lista", " id = " + idProduto,
                null);
    }



    public static List<Lista> getLista(Context contexto){
        List<Lista> lista = new ArrayList<>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM lista ORDER BY nomelista",
                null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Lista p = new Lista();
                p.setId(  cursor.getInt( 0 ) );
                p.setNomelista( cursor.getString( 1 ) );
                lista.add( p );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }

    public static Lista getListaById(Context contexto, int idProduto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sql = "SELECT * FROM lista WHERE id = " + idProduto;
        Cursor cursor = db.rawQuery( sql ,null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Lista p = new Lista();
            p.setId(  cursor.getInt( 0 ) );
            p.setNomelista( cursor.getString( 1 ) );


            return p;
        }else {
            return null;
        }
    }






}
