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

public class Login extends AppCompatActivity {

    private Button btncadastrologin,btnlogin;
    private EditText txtemail,txtsenha;
    private FirebaseAuth auth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarcomponentes();
        enventoonclick();
    }

    private void enventoonclick(){
        btncadastrologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,CadastroLogin.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtemail.getText().toString().trim();
                String senha = txtsenha.getText().toString().trim();

                Login(email,senha);
            }
        });


    }

    private void Login(String email,String senha){
    auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                alert("Logado Com sucesso");
            }else{
                alert("email ou senha errados");
            }
        }
    });


    }

 private void inicializarcomponentes(){
        auth = FirebaseAuth.getInstance();
     btncadastrologin = findViewById(R.id.btncadastro);
     btnlogin = findViewById(R.id.btnlogin);
     txtemail= findViewById(R.id.txtloginemail);
     txtsenha = findViewById(R.id.txtloginsenha);
 }


    private void alert(String mensagem){
        Toast.makeText(Login.this,mensagem,Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
