package com.bruno.lista_compras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static void inserir(Context contexto, Produto produto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nomeproduto", produto.getNomeproduto() );
        valores.put( "tipoproduto", produto.getTipoproduto() );
        valores.put( "quantidade", produto.getQuantidade() );
        valores.put( "idlista", produto.getIdlista() );


        db.insert("produto" , null , valores );

    }


    public static void editar(Context contexto, Produto produto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "idproduto", produto.getIdproduto() );
        valores.put( "nomeproduto", produto.getNomeproduto() );
        valores.put( "tipoproduto", produto.getTipoproduto() );
        valores.put( "quntidade", produto.getQuantidade() );

        db.update("produto", valores,
                " idproduto = " + produto.getIdproduto(), null);

    }

    public static void excluir(Context contexto, int idProduto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("produto", " idproduto = " + idProduto,
                null);
    }
    public static List<Produto> getbuscaprodutos(Context contexto , int idlista){
        List<Produto> lista = new ArrayList<>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produto WHERE idlista=" +idlista +" ORDER BY tipoproduto ",
                null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Produto p = new Produto();
                p.setIdproduto(  cursor.getInt( 0 ) );
                p.setNomeproduto( cursor.getString( 1 ) );
                p.setTipoproduto( cursor.getString( 2 ) );
                p.setQuantidade( cursor.getString( 3 ) );

                lista.add( p );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }




}
