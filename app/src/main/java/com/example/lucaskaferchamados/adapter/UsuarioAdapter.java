package com.example.lucaskaferchamados.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucaskaferchamados.R;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.model.Usuario;
import com.example.lucaskaferchamados.view.UsuarioActivity;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    Context context;
    ArrayList<Usuario> elementos;


    public UsuarioAdapter (Context context, ArrayList<Usuario> elementos) {
        super(context, R.layout.item_lista_usuarios, elementos);
        this.context = context;
        this.elementos = elementos;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            Usuario objeto = elementos.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_usuarios, parent, false);
            TextView id = rowView.findViewById(R.id.lblId_item_Listusuario);
            TextView login = rowView.findViewById(R.id.lblLogin_item_Listusuario);

            id.setText(String.valueOf(objeto.getId()));
            login.setText(String.valueOf(objeto.getLogin()));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent tela = new Intent(context, UsuarioActivity.class);
                    tela.putExtra("ID", objeto.getId());
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
