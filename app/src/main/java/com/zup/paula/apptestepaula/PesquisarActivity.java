package com.zup.paula.apptestepaula;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class PesquisarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        ListView lista = (ListView) findViewById(R.id.list_contatos);


        SQLiteDatabase db = openOrCreateDatabase("contatos.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT contatos._id, contatos.nome, contatos.endereco, contatos.cidade, contatos.telefone, contatos.email FROM contatos", null);

        String[] from = {"nome", "endereco", "cidade", "telefone","email"};
        int[] to = {R.id.txtNome, R.id.txtEndereco,R.id.txtCidade, R.id.txtTelefone, R.id.txtEmail};


        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.model_pesquisar, cursor, from, to);

        lista.setAdapter(ad);

        db.close();



    }

}
