package com.bruno.lista_compras;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListasProdutos extends AppCompatActivity {


    private ListView lvProdutos;
    private List<Lista> listaDeListas;
    private AdapterProduto adapterProduto;


    private ArrayAdapter<Lista> arrayAdapterLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas_produtos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        lvProdutos = findViewById(R.id.lvlistas);
        listaDeListas = new ArrayList<Lista>();
        arrayAdapterLista = new ArrayAdapter<Lista>(this,android.R.layout.simple_list_item_1,listaDeListas);
        lvProdutos.setAdapter(arrayAdapterLista);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListasProdutos.this,CadastroLista.class);
                startActivity(intent);
            }
        });
         lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent = new Intent(ListasProdutos.this,ListadosProdutos.class);

                 Lista lista = (Lista) adapterView.getItemAtPosition(i);
                 intent.putExtra("idlista",lista.getId());
                 startActivity(intent);
             }
         });
         lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
             @Override
             public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                 excluir( (Lista) adapterView.getItemAtPosition(i));
                 return true;
             }
         });


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
        carregarLista();

    }
    private void excluir(final Lista lista){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Time");
        alerta.setMessage("Confirma a exclusão do Time"
                + lista.getNomelista() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ListaDAO.excluir(ListasProdutos.this, lista.getId());
                carregarLista();
            }
        });
        alerta.show();

    }
    private void carregarLista(){
        List<Lista> lista = ListaDAO.getLista(this);

        if ( lista.size() == 0 ){
            lvProdutos.setEnabled( false );
            Lista fake = new Lista();
            fake.setId(0);
            fake.setNomelista("Lista Vazia!");
            lista.add( fake );
        }else {
            lvProdutos.setEnabled( true );
        }

//ArrayAdapter<Lista> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);

        AdapterProduto adapter = new AdapterProduto(this, lista);

        lvProdutos.setAdapter( adapter );

    }

}
