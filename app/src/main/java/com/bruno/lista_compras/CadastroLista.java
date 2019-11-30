package com.bruno.lista_compras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CadastroLista extends AppCompatActivity {

    private EditText txtnomelista;
    private Button btncadastrolista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lista);

        inicializarcomponentes();
        eventoclik();
    }

    private void inicializarcomponentes(){
         txtnomelista = (EditText) findViewById(R.id.txtnomelista);
         btncadastrolista = (Button) findViewById(R.id.btncadastrarlista);
    }



    private void eventoclik(){

        btncadastrolista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nomelista = txtnomelista.getText().toString();

               if (nomelista.isEmpty()){
                   AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroLista.this);
                   alerta.setIcon(android.R.drawable.ic_dialog_alert);
                   alerta.setTitle("Atenção");
                   alerta.setMessage("Você deve informar o nome da lista");
                   alerta.setPositiveButton("ok",null);
                   alerta.show();


               }else{
                   Lista lista = new Lista();

                   lista.setNomelista(nomelista);

                   ListaDAO.inserir(CadastroLista.this,lista);

                   finish();




               }

            }
        });


    }
     private void  limparcampos(){
        txtnomelista.setText("");

     }


}
