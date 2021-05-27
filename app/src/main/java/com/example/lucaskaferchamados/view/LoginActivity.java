package com.example.lucaskaferchamados.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.controller.UsuarioController;
import com.example.lucaskaferchamados.model.Usuario;


public class LoginActivity extends AppCompatActivity {
    EditText txtUsuario, txtSenha;
    Button Btlogar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;
        Btlogar = findViewById(R.id.button_login);
        txtUsuario = findViewById(R.id.editTextNome_login);
        txtSenha = findViewById(R.id.editTextSenha_login);


        Btlogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioController controller = new UsuarioController(context);
                Usuario user = controller.login(txtUsuario.getText().toString(), txtSenha.getText().toString() );

                if (user != null)
                {
                    Intent tela = new Intent(context, MenuActivity.class);
                    startActivity(tela);
                }else{
                    Globais.exibirMensagens(context,"Usuário ou senha invalido");
                }
            }
        });
    }
}