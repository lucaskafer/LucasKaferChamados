package com.example.lucaskaferchamados.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.adapter.OrdemServicoAdapter;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.controller.OrdemServicoController;
import com.example.lucaskaferchamados.model.OrdemServico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ListaOrdemServicoActivity extends AppCompatActivity {


    ListView ltvOSServicos;
    FloatingActionButton btnVoltar;
    Context context;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os);

        context = ListaOrdemServicoActivity.this;
        ltvOSServicos= findViewById(R.id.ltv_Lista_ordemservico);
        btnVoltar=findViewById(R.id.btnVoltar_Lista_ordemservico);

        btnVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                Globais.exibirMensagens(context, "Tela Inicial");
                Globais.abrirActivity(context, MenuActivity.class, false);
            }
        });
    }
    @Override
    protected void onResume () {
        super.onResume();
        atualizarLista();
    }
    public void atualizarLista()
    {
        try {
            // buscar todos e preencher
            OrdemServicoController controller = new OrdemServicoController(context);
            ArrayList<OrdemServico> lista = controller.lista();

            if (lista!= null)
            {
                ArrayAdapter adapter = new OrdemServicoAdapter(context,lista);
                ltvOSServicos.setAdapter(adapter);
            }
        }catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
        }
    }
}
