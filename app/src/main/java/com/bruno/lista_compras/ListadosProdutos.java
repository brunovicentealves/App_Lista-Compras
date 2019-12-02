package com.bruno.lista_compras;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.List;

public class ListadosProdutos extends AppCompatActivity {

    private ListView lvProdutos;
    private Integer idlista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listados_produtos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvProdutos = findViewById(R.id.lvprodutos);
        idlista = getIntent().getExtras().getInt("idlista");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadosProdutos.this,CadastroProduto.class);
                intent.putExtra("idlista",idlista);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        carregarlista();
    }


    private void carregarlista(){
        List<Produto> lista = ProdutoDAO.getbuscaprodutos(ListadosProdutos.this, idlista);

        if ( lista.size() == 0 ){
            lvProdutos.setEnabled( false );
            Produto fake = new Produto();
            fake.setNomeproduto("Lista Vazia!");
            fake.setQuantidade("");

            lista.add( fake );
        }else {
            lvProdutos.setEnabled( true );
        }



        AdapterListaProdutos adapter = new AdapterListaProdutos(ListadosProdutos.this, lista);

        lvProdutos.setAdapter( adapter );

    }




    }

