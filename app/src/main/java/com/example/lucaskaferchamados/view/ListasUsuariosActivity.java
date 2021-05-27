package com.example.lucaskaferchamados.view;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.adapter.UsuarioAdapter;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.controller.UsuarioController;
import com.example.lucaskaferchamados.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListasUsuariosActivity extends AppCompatActivity {

    ListView ltvUsuarios;
    FloatingActionButton btnAddUsuario, btnVoltar;
    Context context;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listasusuarios);


        context = ListasUsuariosActivity.this;
        ltvUsuarios= findViewById(R.id.ltvUsuarios_Lista_Usuario);
        btnAddUsuario = findViewById(R.id.btnAddUsuarios_lista_usuario);



        btnAddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Globais.abrirActivity(context, UsuarioActivity.class, false);

            }
        });
    }
    @Override
    protected void onResume () {
        super.onResume();
        atualizarLista();
    }
    private void atualizarLista(){
        try{
            //buscar todos os usuarios e preencher em um List
            UsuarioController controller = new UsuarioController(context);
            ArrayList<Usuario> lista = controller.lista();
            if(lista != null){
                //ArrayAdapter<Usuario> adapter =
                //      new ArrayAdapter<Usuario>(context, android.R.layout.simple_list_item_1, lista);
                ArrayAdapter adapter = new UsuarioAdapter(context, lista);
                ltvUsuarios.setAdapter(adapter);
           }
        }catch (Exception ex){

        }
    }
}
