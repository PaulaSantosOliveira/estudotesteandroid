package com.zup.paula.apptestepaula;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cria Banco
        SQLiteDatabase db = openOrCreateDatabase("contatos.db", Context.MODE_PRIVATE,null);


        StringBuilder sqlContatos = new StringBuilder();
        sqlContatos.append("CREATE TABLE IF NOT EXISTS [contatos] (");
        sqlContatos.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlContatos.append("nome VARCHAR(100),");
        sqlContatos.append("endereco VARCHAR(200),");
        sqlContatos.append("cidade VARCHAR(100),");
        sqlContatos.append("telefone VARCHAR(20),");
        sqlContatos.append("email VARCHAR(20));");
        db.execSQL(sqlContatos.toString());

        db.execSQL("DELETE FROM contatos");

        db.close();


        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        Button btnPesquisar = (Button) findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),PesquisarActivity.class));
            }
        });

    }
    private void salvar(){
        SQLiteDatabase db = openOrCreateDatabase("contatos.db", Context.MODE_PRIVATE, null);

        EditText nome = (EditText) findViewById(R.id.txtNome);
        EditText endereco = (EditText) findViewById(R.id.txtEndereco);
        EditText cidade = (EditText) findViewById(R.id.txtCidade);
        EditText telefone = (EditText) findViewById(R.id.txtTelefone);
        EditText email = (EditText) findViewById(R.id.txtEmail);

        if(!nome.getText().toString().trim().equals("")
                &&!endereco.getText().toString().trim().equals("")
                && !cidade.getText().toString().trim().equals("")
                &&!telefone.getText().toString().trim().equals("")
                && !email.getText().toString().trim().equals("")){
            ContentValues values = new ContentValues();
            values.put("nome", nome.getText().toString());
            values.put("endereco", endereco.getText().toString());
            values.put("cidade", cidade.getText().toString());
            values.put("telefone", telefone.getText().toString());
            values.put("email", email.getText().toString());

            if(db.insert("contatos", "_id", values)>0){
                Toast.makeText(getBaseContext(),"Sucesso",Toast.LENGTH_LONG).show();
                nome.setText(null);
                endereco.setText(null);
                cidade.setText(null);
                telefone.setText(null);
                email.setText(null);
            }else{
                Toast.makeText(getBaseContext(),"Erro",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getBaseContext(),"Preencha Todos os campos!",Toast.LENGTH_LONG).show();
        }


    }





}
