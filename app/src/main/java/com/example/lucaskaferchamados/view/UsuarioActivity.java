package com.example.lucaskaferchamados.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.controller.UsuarioController;
import com.example.lucaskaferchamados.model.Usuario;


public class UsuarioActivity extends AppCompatActivity {

    Context context;
    //Usuario objeto;
    TextView lblId;
    EditText txtSenha, txtEmail, txtTelefone, txtLogin;
    UsuarioController usuarioController;
    Usuario objUsuario;
    Button btnExcluir;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        context = UsuarioActivity.this;

        lblId = findViewById(R.id.lblIdUsuario_CadUsuario);

        txtSenha = findViewById(R.id.txtSenhaUsuario_CadUsuario);
        txtLogin = findViewById(R.id.txtLoginUsuario_CadUsuario);
        txtEmail = findViewById(R.id.txtEmailUsuario_CadUsuario);
        txtTelefone = findViewById(R.id.txtTelefoneUsuario_CadUsuario);

        btnExcluir = findViewById(R.id.btnExcluir_CadUsuario);


        // capturar o id da tela listagem
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.getInt("ID") > 0) {
            // objeto = busca do controller
            int codigo = bundle.getInt("ID");
            usuarioController = new UsuarioController(context);
            objUsuario = usuarioController.buscar(codigo);

            if (objUsuario != null) {
                preencheCampos(objUsuario);
            }
        } else {
            btnExcluir.setVisibility(View.INVISIBLE);
        }

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                usuarioController = new UsuarioController(context);
                objUsuario.setId(Integer.parseInt(lblId.getText().toString()));

                boolean retorno = usuarioController.excluir(objUsuario);

                if(retorno){
                    Globais.exibirMensagens(context,"Usuario Excluido");
                    finish();
                }
            }
        });

    }

    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean retorno;

        switch (id){

            case R.id.action_ok:

                objUsuario = new Usuario();
                UsuarioController objController = new UsuarioController(context);


                objUsuario.setLogin(txtLogin.getText().toString());
                objUsuario.setEmail(txtEmail.getText().toString());
                objUsuario.setTelefone(txtTelefone.getText().toString());


                String senhaMd5 = Globais.md5(txtSenha.getText().toString());
                objUsuario.setSenha(senhaMd5);

                if(!lblId.getText().equals("ID")){
                    //alterar
                    objUsuario.setId(Integer.parseInt(lblId.getText().toString()));
                    retorno = objController.alterar(objUsuario);
                    if(retorno){
                        Globais.exibirMensagens(context, "Usuário alterado com sucesso");
                    }
                }else{
                    //incluir
                    retorno = objController.incluir(objUsuario);
                    if(retorno){
                        Globais.exibirMensagens(context, "Usuário incluído com sucesso");
                    }
                }

                finish();

            case R.id.action_cancel:
                finish();

        }

        return super.onOptionsItemSelected(item);
    }


    private void preencheCampos (Usuario objeto) {

        try {
            lblId.setText(String.valueOf(objeto.getId()));
            txtLogin.setText(objeto.getLogin());
            txtLogin.setEnabled(false);
            txtTelefone.setText(objeto.getTelefone());
            txtEmail.setText(objeto.getEmail());


        } catch (Exception e) {
            Globais.exibirMensagens(context, e.getMessage());

        }
    }

}