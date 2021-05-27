package com.example.lucaskaferchamados.view;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.adapter.ServicoAdapter;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.controller.ServicoController;
import com.example.lucaskaferchamados.model.Servicos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaServicosActivity extends AppCompatActivity {

    ListView ltvServicos;
    FloatingActionButton btnADDServicos;
    Context context;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaservicos);
        context = ListaServicosActivity.this;
        ltvServicos= findViewById(R.id.ltv_Servicos_Lista_servicos);

        btnADDServicos = findViewById(R.id.btnAdd_Servicos_Lista_servicos);

        btnADDServicos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                Globais.exibirMensagens(context, "Adicionar serviços");
                Globais.abrirActivity(context, ServicosActivity.class, false);
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
            ServicoController controller = new ServicoController(context);
            ArrayList<Servicos> lista = controller.lista();

            if (lista!= null)
            {
                ArrayAdapter adapter = new ServicoAdapter(context,lista);
                ltvServicos.setAdapter(adapter);
            }
        }catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
        }
    }
}
