package com.example.lucaskaferchamados.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.controller.ServicoController;
import com.example.lucaskaferchamados.model.Servicos;
import com.example.lucaskaferchamados.model.StatusServicos;

import java.util.ArrayList;


public class ServicosActivity extends AppCompatActivity  {

    Context context;
    TextView lblId_servico;
    EditText txtNome_servico, txtValor_servico, txtStatus_servico ,txtObservacao_servicos;
    ServicoController servicoController;
    Spinner spiStatusServico;
    Servicos objServico;
    Button btnExcluir, btnVoltar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        context = ServicosActivity.this;

        lblId_servico = findViewById(R.id.lblIdServicos_CadServicos);
        txtNome_servico = findViewById(R.id.txtNomeServico_CadServico);
        txtValor_servico = findViewById(R.id.txtValorServico_CadServico);
        txtObservacao_servicos = findViewById(R.id.txtObservacao_CadServico);
        spiStatusServico = findViewById(R.id.spiTipoUsuario_CadServicos);

        btnExcluir = findViewById(R.id.btnExcluir_CadServicos);

        // Spinner Lista de Status
        ArrayList<StatusServicos> array_status = new ArrayList<>();
        array_status.add(new StatusServicos(0, "Selecione..."));
        array_status.add(new StatusServicos(1, "Aberto"));
        array_status.add(new StatusServicos(2, "Finalizado"));

        ArrayAdapter<StatusServicos> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_dropdown_item_1line, array_status);

        spiStatusServico.setAdapter(adapter);

        // capturar o id da tela listagem
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getInt("ID") > 0) {
            // objeto = busca do controller
            int codigo = bundle.getInt("ID");
            servicoController = new ServicoController(context);
            objServico = servicoController.buscar(codigo);

            if (objServico != null) {
                preencheCampos(objServico);
            }
        } else {
            btnExcluir.setVisibility(View.INVISIBLE);
        }

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                servicoController = new ServicoController(context);
                objServico.setId_servico(Integer.parseInt(lblId_servico.getText().toString()));

                boolean retorno = servicoController.excluir(objServico);

                if (retorno) {
                    Globais.exibirMensagens(context, "Serviço Excluido");
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

                objServico = new Servicos();
                ServicoController objController = new ServicoController(context);

                objServico.setNome_servico(txtNome_servico.getText().toString());
                objServico.setValor_servico(Float.parseFloat(txtValor_servico.getText().toString()));
                objServico.setObservacao_servico(txtObservacao_servicos.getText().toString());
                // incluit spiStatusServico

                if(!lblId_servico.getText().equals("ID")){
                    //alterar
                    objServico.setId_servico(Integer.parseInt(lblId_servico.getText().toString()));
                    retorno = objController.alterar(objServico);
                    if(retorno){
                        Globais.exibirMensagens(context, "Serviço alterado com sucesso");
                    }
                }else{
                    //incluir
                    retorno = objController.incluir(objServico);
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
            lblId_servico.setText(String.valueOf(objeto.getId_servico()));
            txtNome_servico.setText(objeto.getNome_servico());
            txtObservacao_servicos.setText(String.valueOf(objeto.getObservacao_servico()));
            //txtStatus_servico.setEnabled(false);
            txtValor_servico.setText(String.valueOf(objeto.getValor_servico()));


        } catch (Exception e) {
            Globais.exibirMensagens(context, e.getMessage());

        }
    }

}