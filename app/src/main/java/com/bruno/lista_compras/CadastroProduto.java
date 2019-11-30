package com.bruno.lista_compras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CadastroProduto extends AppCompatActivity {
    private EditText txtnomeproduto;
    private EditText txtprecoproduto;
    private Button btntteste;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
            inicializarcomponentes();
            inicializarfirebase();
            eventoclik();





    }
    private void inicializarfirebase(){
        FirebaseApp.initializeApp(CadastroProduto.this);
       firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();




    }

    private void inicializarcomponentes(){

        txtnomeproduto= (EditText) findViewById(R.id.txtnomeproduto1);
        txtprecoproduto = (EditText) findViewById(R.id.txtquantidade1);

        btntteste = (Button) findViewById(R.id.btntteste);
    }


    private void eventoclik(){
        btntteste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto produto = new Produto();
                produto.setUid(UUID.randomUUID().toString());
                produto.setNome(txtnomeproduto.getText().toString());
                produto.setQuantidade(txtprecoproduto.getText().toString());
                databaseReference.child("produto").child(produto.getUid()).setValue(produto);
                limpardados();
                finish();
            }
        });


    }

    private void limpardados(){
        txtnomeproduto.setText("");
        txtprecoproduto.setText("");



    }
}
