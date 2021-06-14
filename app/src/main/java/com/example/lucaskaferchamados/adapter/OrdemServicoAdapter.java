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
import com.example.lucaskaferchamados.controller.ServicoController;
import com.example.lucaskaferchamados.controller.UsuarioController;
import com.example.lucaskaferchamados.model.OrdemServico;
import com.example.lucaskaferchamados.model.Servicos;
import com.example.lucaskaferchamados.model.Usuario;
import com.example.lucaskaferchamados.view.OrdemServicoActivity;

import java.util.ArrayList;

public class OrdemServicoAdapter extends ArrayAdapter<OrdemServico> {

    Context context;
    ArrayList<OrdemServico> elementos;

    public OrdemServicoAdapter (Context context, ArrayList<OrdemServico> elementos) {
        super(context, R.layout.item_ordemservico, elementos);
        this.context = context;
        this.elementos = elementos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            OrdemServico objeto = elementos.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // toda vez que passa por um item da lista, os elementos são associados
            @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.item_ordemservico, parent, false);




            TextView nomeCliente = rowView.findViewById(R.id.lbl_NomeCliente_item_osfinalizada);
            TextView nomeServico = rowView.findViewById(R.id.lbl_NomeServico_item_osfinalizada);
            TextView valor = rowView.findViewById(R.id.lblValor_item_osfinalizada);
            TextView observacao = rowView.findViewById(R.id.lblObservacao_item_osfinalizada);

            UsuarioController objUserController = new UsuarioController(context);
            Usuario objUser = objUserController.buscar(objeto.getId_os());

            ServicoController objServController = new ServicoController(context);
            Servicos objSer= objServController.buscar(objeto.getId_os());

            if(objSer != null){
                nomeServico.setText(objSer.getNome_servico());
            }

            if(objUser != null){
                nomeCliente.setText(objUser.getLogin());
                }



            valor.setText(String.valueOf(objeto.getValor_servico()));
            observacao.setText(String.valueOf(objeto.getObservacao_servico()));
            //status_servico.setText(String.valueOf(objeto.getStatus_servico()));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent tela = new Intent(context, OrdemServicoActivity.class);
                    tela.putExtra("ID", objeto.getId_os());
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
