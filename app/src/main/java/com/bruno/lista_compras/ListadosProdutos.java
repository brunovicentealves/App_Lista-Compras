package com.bruno.lista_compras;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        getSupportActionBar().setTitle("Produtos da Lista");
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


       lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               excluir( (Produto) adapterView.getItemAtPosition(i)  );
           }
       });
    }


    private void excluir(final Produto produto){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do produto "
                + produto.getNomeproduto() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProdutoDAO.excluir(ListadosProdutos.this, produto.getIdproduto());
                carregarlista();
            }
        });
        alerta.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

