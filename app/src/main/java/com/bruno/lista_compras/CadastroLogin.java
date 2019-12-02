package com.bruno.lista_compras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroLogin extends AppCompatActivity {
    private EditText txtemail,txtsenha;
    private FirebaseAuth auth ;
    private Button btnvoltar;
    private Button btncadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);
        getSupportActionBar().setTitle("Cadastro no APP ");

        inicializarComponentes();
        eventoclicks();
    }

    private void eventoclicks(){

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtemail.getText().toString().trim();
                String senha = txtsenha.getText().toString().trim();

                criaruser(email,senha);

            }
        });
    }


    private void criaruser(String email ,String senha){
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(CadastroLogin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alert( "Cadastrado com Sucesso");

                    Intent intent = new Intent(CadastroLogin.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    alert("erro de cadastro");
                }
            }
        });


    }

    private void inicializarComponentes(){
        btnvoltar = findViewById(R.id.btnvoltar);
        btncadastrar = findViewById(R.id.btncadastrologinsalvar);
        txtemail = findViewById(R.id.txtcadastraloginemail);
        txtsenha = findViewById(R.id.txtcadastraloginsenha);
        auth = FirebaseAuth.getInstance();
    }


    private void alert(String mensagem){
        Toast.makeText(CadastroLogin.this,mensagem,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
