package com.bruno.lista_compras;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Button btnlogout,btncadastroproduto;
    private EditText txtid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarcomponentes();

        eventoonclick();

    }

    private void inicializarcomponentes(){
        btnlogout = findViewById(R.id.btnlogout);
        txtid = findViewById(R.id.txtid);
        btncadastroproduto = findViewById(R.id.btncadastroproduto);
    }

    private void eventoonclick(){
btnlogout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Conexao.logaut();
        finish();
    }
});

btncadastroproduto.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,CadastroProduto.class);
        startActivity(intent);
    }
});

    }


    @Override
    protected void onStart() {
        super.onStart();
        auth =Conexao.getFirebaseAuth();
        user= Conexao.getFirebaseUser();
        verificaUser();
    }


    private void verificaUser(){
        if (user == null){

        finish();

        }else{
        txtid.setText("ID: "+user.getUid());

        }


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
}
