package com.example.lucaskaferchamados.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.controller.OrdemServicoController;
import com.example.lucaskaferchamados.controller.ServicoController;
import com.example.lucaskaferchamados.controller.UsuarioController;
import com.example.lucaskaferchamados.model.OrdemServico;
import com.example.lucaskaferchamados.model.Servicos;
import com.example.lucaskaferchamados.model.Usuario;

import java.util.ArrayList;

public class OrdemServicoActivity extends AppCompatActivity {


    Context context;
    TextView lblId;
    EditText txtNome_servico, txtValor_servico, txtStatus_servico;
    OrdemServico objOrdemServico;
    Button btnExcluir;

    Spinner spiClientes, spiServicos;

    ServicoController servicoController;
    UsuarioController usuarioController;
    OrdemServicoActivity incluirServicosActivity;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluirordemservico);

        context = OrdemServicoActivity.this;


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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean retorno;

        switch (id){
            case R.id.action_ok:

                objOrdemServico = new OrdemServico();
                OrdemServicoController objController = new OrdemServicoController(context);

                objOrdemServico.setNome_servico(txtNome_servico.getText().toString());
                objOrdemServico.setValor_servico(Float.parseFloat(txtValor_servico.getText().toString()));

                // incluit spiStatusServico

                if(!lblId.getText().equals("ID")){
                    //alterar
                    objOrdemServico.setId_os(Integer.parseInt(lblId.getText().toString()));
                    retorno = objController.alterar(objOrdemServico);
                    if(retorno){
                        Globais.exibirMensagens(context, "Serviço alterado com sucesso");
                    }
                }else{
                    //incluir
                    retorno = objController.incluir(objOrdemServico);
                    if(retorno){
                        Globais.exibirMensagens(context, "Serviço incluído com sucesso");
                    }
                }
                finish();

            case R.id.action_cancel:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void preencheCampos (Servicos objeto) {
        try {
            lblId.setText(String.valueOf(objeto.getId_servico()));
            txtNome_servico.setText(objeto.getNome_servico());

            //txtStatus_servico.setEnabled(false);
            txtValor_servico.setText(String.valueOf(objeto.getValor_servico()));


        } catch (Exception e) {
            Globais.exibirMensagens(context, e.getMessage());

        }
    }

}