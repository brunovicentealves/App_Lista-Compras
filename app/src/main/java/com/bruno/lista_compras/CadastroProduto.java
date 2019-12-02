package com.bruno.lista_compras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastroProduto extends AppCompatActivity {
    private EditText txtnomeproduto;
    private EditText txtquantidadeproduto;
    private Button btntteste;
    private Spinner spinnercategoria;
    private Integer idlista;


    String categoria [] ={"Açogue","Padaria","Higiene",};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        idlista = getIntent().getExtras().getInt("idlista");
            inicializarcomponentes();
            eventoclik();
    }

    private void inicializarcomponentes(){

        txtnomeproduto= (EditText) findViewById(R.id.txtnomeproduto);
        txtquantidadeproduto = (EditText) findViewById(R.id.txtquantidade);

        btntteste = (Button) findViewById(R.id.btntteste);
        spinnercategoria = (Spinner) findViewById(R.id.spinner);

    }


    private void eventoclik(){
        btntteste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeproduto = txtnomeproduto.getText().toString();
                String quantidade = txtquantidadeproduto.getText().toString();
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

                     produto.setTipoproduto("vazio");

                     produto.setIdlista(idlista);

                     ProdutoDAO.inserir(CadastroProduto.this,produto);


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
