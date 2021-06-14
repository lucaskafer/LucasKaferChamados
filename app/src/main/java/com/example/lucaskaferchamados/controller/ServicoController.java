package com.example.lucaskaferchamados.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lucaskaferchamados.config.DadosOpenHelper;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.config.Tabelas;
import com.example.lucaskaferchamados.model.Servicos;
import java.util.ArrayList;

public class ServicoController {

    Context context;
    SQLiteDatabase conexao;

    public ServicoController (Context context) {
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }
    // metodos ( buscar, listar, alterar, incluir, excluir)

    public Servicos buscar (int id) {
        try {
            Servicos objeto = null;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM ");
            sql.append(Tabelas.TB_SERVICOS);
            sql.append(" WHERE id_servicos = '" + id + "'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.getCount() > 0) {

                resultado.moveToFirst();
                objeto = new Servicos();

                objeto.setId_servico(resultado.getInt(resultado.getColumnIndexOrThrow("id_servicos")));
                objeto.setNome_servico(resultado.getString(resultado.getColumnIndexOrThrow("nome_servicos")));
                objeto.setValor_servico(resultado.getFloat(resultado.getColumnIndexOrThrow("valor_servicos")));
                objeto.setObservacao_servico(resultado.getString(resultado.getColumnIndexOrThrow("observacao_servicos")));
                objeto.setStatus_servico(resultado.getString(resultado.getColumnIndexOrThrow("status_servicos")));

            }

            return objeto;

        } catch (Exception ex) {
            Globais.exibirMensagens(context, ex.getMessage());
            return null;
        }
    }

    public boolean incluir (Servicos objeto) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("nome_servicos", objeto.getNome_servico());
            valores.put("valor_servicos", objeto.getValor_servico());
            valores.put("observacao_servicos", objeto.getObservacao_servico());
            valores.put("status_servicos", objeto.getStatus_servico());

            conexao.insertOrThrow(Tabelas.TB_SERVICOS, null, valores);
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;
        }
    }

    public boolean alterar (Servicos objeto) {
        try {

            ContentValues valores = new ContentValues();

            valores.put("id_servicos", objeto.getId_servico());
            valores.put("nome_servicos", objeto.getNome_servico());
            valores.put("valor_servicos", objeto.getValor_servico());
            valores.put("observacao_servicos", objeto.getObservacao_servico());
            valores.put("status_servicos", objeto.getStatus_servico());

            String[] parametros= new String[1];
            parametros[0] = String.valueOf(objeto.getId_servico());

            conexao.update(Tabelas.TB_SERVICOS, valores, "id_servicos = ? ", parametros );
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;
        }
    }

    public boolean excluir (Servicos objeto) {
        try {

            String[] parametros= new String[1];
            parametros[0] = String.valueOf(objeto.getId_servico());

            conexao.delete(Tabelas.TB_SERVICOS, "id_servicos = ?", parametros );
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;
        }
    }


    public ArrayList<Servicos> lista () {

        ArrayList<Servicos> listagem = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_SERVICOS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.moveToFirst()) {
                Servicos objeto;
                do {
                    objeto = new Servicos();
                    objeto.setId_servico(resultado.getInt(resultado.getColumnIndexOrThrow("id_servicos")));
                    objeto.setNome_servico(resultado.getString(resultado.getColumnIndexOrThrow("nome_servicos")));
                    objeto.setValor_servico(resultado.getFloat(resultado.getColumnIndexOrThrow("valor_servicos")));
                    objeto.setObservacao_servico(resultado.getString(resultado.getColumnIndexOrThrow("observacao_servicos")));
                    objeto.setStatus_servico(resultado.getString(resultado.getColumnIndexOrThrow("status_servicos")));

                    listagem.add(objeto);

                } while (resultado.moveToNext());

            }
            return listagem;

        } catch (Exception ex) {
            Globais.exibirMensagens(context, ex.getMessage());
            return listagem;
        }
    }
}

