package com.bruno.lista_compras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastroProduto extends AppCompatActivity {
    private EditText txtnomeproduto;
    private EditText txtquantidadeproduto;
    private Button btntteste;
    private Spinner spinnercategoria;
    private Integer idlista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        getSupportActionBar().setTitle("Cadastro Produto ");
        idlista = getIntent().getExtras().getInt("idlista");
            inicializarcomponentes();
            eventoclik();
            spinnercategoria = (Spinner) findViewById(R.id.spinnercategoria);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(CadastroProduto.this,R.array.spinnercategoria,android.R.layout.simple_spinner_item
        );

        spinnercategoria.setAdapter(adapter);


    }

    private void inicializarcomponentes(){

        txtnomeproduto= (EditText) findViewById(R.id.txtnomeproduto);
        txtquantidadeproduto = (EditText) findViewById(R.id.txtquantidade);

        btntteste = (Button) findViewById(R.id.btntteste);
        spinnercategoria = (Spinner) findViewById(R.id.spinnercategoria);

    }


    private void eventoclik(){
        btntteste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeproduto = txtnomeproduto.getText().toString();
                String quantidade = txtquantidadeproduto.getText().toString();
                String categoria = spinnercategoria.getSelectedItem().toString();
                 if(nomeproduto.isEmpty()){
                     AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroProduto.this);
                     alerta.setIcon( android.R.drawable.ic_dialog_alert);
                     alerta.setTitle("Atenção!");
                     alerta.setMessage("Você deve informar o nome do produto.");
                     alerta.setPositiveButton("OK", null);
                     alerta.show();

                 }else{
                    Produto produto = new Produto();

                     produto.setNomeproduto(nomeproduto);

                     produto.setQuantidade(quantidade);

                     produto.setTipoproduto(categoria);

                     produto.setIdlista(idlista);

                     ProdutoDAO.inserir(CadastroProduto.this,produto);
                    finish();

                 }
                limpardados();
            }
        });

    }

    private void limpardados(){
        txtnomeproduto.setText("");
        txtquantidadeproduto.setText("");



    }
}
