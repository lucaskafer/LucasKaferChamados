package com.example.lucaskaferchamados.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.controller.ServicoController;
import com.example.lucaskaferchamados.controller.UsuarioController;
import com.example.lucaskaferchamados.model.Servicos;
import com.example.lucaskaferchamados.model.Usuario;

import java.util.ArrayList;

public class AbrirOrdemServicoActivity extends AppCompatActivity {


    Context context;
    TextView lblId;
    EditText txtNome_servico, txtValor_servico, txtStatus_servico;
    Servicos objServico;
    Button btnExcluir;

    Spinner spiClientes, spiServicos;

    ServicoController servicoController;
    UsuarioController usuarioController;
    AbrirOrdemServicoActivity incluirServicosActivity;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluirordemservico);

        context = AbrirOrdemServicoActivity.this;


        lblId = findViewById(R.id.lblIdServicos_CadServicos);
        txtNome_servico = findViewById(R.id.txtNomeServico_CadServico);
        txtValor_servico = findViewById(R.id.txtValorServico_CadServico);
        spiClientes = findViewById(R.id.spiClientes_IncluirOS);
        spiServicos = findViewById(R.id.spiServico_IncluirOS);

        //Spinner buscar Usuarios
        UsuarioController controller = new UsuarioController(context);
        ArrayList<Usuario> listaUsuario = controller.lista();
        if(listaUsuario != null){
            ArrayAdapter<Usuario> adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, listaUsuario);
            spiClientes.setAdapter(adapter);
        }

        //Spinner buscar servicos
        ServicoController controllerServico = new ServicoController(context);
        ArrayList<Servicos> listaServico = controllerServico.lista();
        if(listaServico != null){
            ArrayAdapter<Servicos> adapter  = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, listaServico);
            spiServicos.setAdapter(adapter);

        }
    }

    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);
        return super.onCreateOptionsMenu(menu);
    }



}