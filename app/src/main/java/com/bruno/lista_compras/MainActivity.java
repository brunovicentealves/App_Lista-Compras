package com.bruno.lista_compras;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Button btnlogout;
    private EditText txtemail,txtsenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarcomponentes();
        eventoonclick();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void inicializarcomponentes(){
        btnlogout = findViewById(R.id.btnlogout);
        txtemail = findViewById(R.id.txtemail);
        txtsenha = findViewById(R.id.txtsenha);
    }

    private void eventoonclick(){
btnlogout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Conexao.logaut();
        finish();
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

        txtemail.setText("Email : "+user.getEmail());
        txtsenha.setText("ID: "+user.getUid());

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
