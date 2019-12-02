package com.bruno.lista_compras;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {


    private List<Produto> listaProdutos;
    private Context context;
    private LayoutInflater inflater;

    public AdapterListaProdutos(Context context, List<Produto> listaProdutos){
        this.context = context;
        this.listaProdutos = listaProdutos;
        this.inflater = LayoutInflater.from( context );
    }



    @Override
    public int getCount() {
        return listaProdutos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaProdutos.get( i );
    }

    @Override
    public long getItemId(int i) {
        return listaProdutos.get( i ).getIdproduto();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemSuporte item;

        if ( view == null){
            view = inflater.inflate(R.layout.layout_produto, null);
            item = new ItemSuporte();

            item.tvnomeproduto = (TextView) view.findViewById(R.id.tvlistanomeproduto);
            item.tvquantidade = (TextView) view.findViewById(R.id.tvquantidade);
            item.layout = (LinearLayout) view.findViewById(R.id.layout);
            view.setTag( item );
        }else {
            item = (ItemSuporte) view.getTag();
        }

        Produto lis = listaProdutos.get( i );
        item.tvnomeproduto.setText( lis.getNomeproduto());
        item.tvquantidade.setText( lis.getQuantidade() );

        if ( lis.getNomeproduto().equals( "Lista Vazia!" )){
            item.tvquantidade.setText( " " );

        }

        if ( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.WHITE);
        }else {
            item.layout.setBackgroundColor( Color.rgb(230, 230, 230) );
        }


        return view;
    }


    private class ItemSuporte{
        TextView tvquantidade, tvnomeproduto;
        LinearLayout layout;
    }
}
