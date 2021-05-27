package com.example.lucaskaferchamados.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.model.Servicos;
import com.example.lucaskaferchamados.view.ServicosActivity;

import java.util.ArrayList;

public class ServicoAdapter extends ArrayAdapter<Servicos> {

    Context context;
    ArrayList<Servicos> elementos;


    public ServicoAdapter (Context context, ArrayList<Servicos> elementos) {
        super(context, R.layout.item_lista_servicos, elementos);
        this.context = context;
        this.elementos = elementos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            Servicos objeto = elementos.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // toda vez que passa por um item da lista, os elementos são associados
            @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.item_lista_servicos, parent, false);


            TextView id = rowView.findViewById(R.id.lblId_item_ListaServico);
            TextView nome = rowView.findViewById(R.id.lblNome_item_ListaServico);
            TextView valor = rowView.findViewById(R.id.lblValor_item_ListaServico);
            //TextView status_servico = rowView.findViewById(R.id.lblStatusServicos_CadServicos);

            id.setText(String.valueOf(objeto.getId_servico()));
            nome.setText(String.valueOf(objeto.getNome_servico()));
            valor.setText(String.valueOf(objeto.getValor_servico()));
            //status_servico.setText(String.valueOf(objeto.getStatus_servico()));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent tela = new Intent(context, ServicosActivity.class);
                    tela.putExtra("ID", objeto.getId_servico());
                    context.startActivity(tela);
                }
            });
            return rowView;
        }
        catch (Exception ex){
            Globais.exibirMensagens(context, ex.getMessage());
            return null;
        }
    }
}
