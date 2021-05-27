package com.example.lucaskaferchamados.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.config.Globais;

public class MenuActivity extends AppCompatActivity {

    Context context;
    Button btMenuUsuarios, btMenuServicos, btMenuIncluirOrdemServicos, btIncluirOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.context = context;
        context = MenuActivity.this;
        btMenuUsuarios = findViewById(R.id.button_lista_usuario_Menu);
        btMenuServicos = findViewById(R.id.button_lista_servicos);
        btMenuIncluirOrdemServicos = findViewById(R.id.button_Cadastro_servicos);
        btIncluirOS = findViewById(R.id.button_Vizualizar_OS);

        btMenuUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                try {
                    Intent tela = new Intent(context, ListasUsuariosActivity.class);
                    startActivity(tela);
                }catch (Exception ex)
                {
                    Globais.exibirMensagens(context, "Não foi possivel incluir usuarios");
                }
            }
        });

        btIncluirOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                try {
                    Intent tela = new Intent(context, ListaOSSalvosActivity.class);
                    startActivity(tela);
                }catch (Exception ex)
                {
                    Globais.exibirMensagens(context, "Impossivel incluir OS");
                }
            }

        });

        btMenuServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                try {
                    Intent tela = new Intent(context, ListaServicosActivity.class);
                    startActivity(tela);
                }catch (Exception ex)
                {
                    Globais.exibirMensagens(context, "Não foi possivel abrir Cad_serviços");
                }
            }
        });
        btMenuIncluirOrdemServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                try {
                    Intent tela = new Intent(context, AbrirOrdemServicoActivity.class);
                    startActivity(tela);

                }catch (Exception ex)
                {
                    Globais.exibirMensagens(context, "Não foi possivel abrir Incluir Serviços");
                }
            }
        });
    }
}

