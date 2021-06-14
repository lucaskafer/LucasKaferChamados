package com.example.lucaskaferchamados.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lucaskaferchamados.config.DadosOpenHelper;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.config.Tabelas;
import com.example.lucaskaferchamados.model.OrdemServico;
import com.example.lucaskaferchamados.model.Servicos;

import java.util.ArrayList;

public class OrdemServicoController {

    Context context;
    SQLiteDatabase conexao;

    public OrdemServicoController (Context context) {
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }

    // metodos ( buscar, listar, alterar, incluir, excluir)

    public OrdemServico buscar (int id) {
        try {
            OrdemServico objeto = null;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM ");
            sql.append(Tabelas.TB_OS);
            sql.append(" WHERE id_servicos = '" + id + "'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.getCount() > 0) {

                resultado.moveToFirst();
                objeto = new OrdemServico();

                objeto.setId_os(resultado.getInt(resultado.getColumnIndexOrThrow("id_servicos")));
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

    public boolean incluir (OrdemServico objeto) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("nome_servicos", objeto.getNome_servico());
            valores.put("valor_servicos", objeto.getValor_servico());
            valores.put("observacao_servicos", objeto.getObservacao_servico());
            valores.put("status_servicos", objeto.getStatus_servico());

            conexao.insertOrThrow(Tabelas.TB_OS, null, valores);
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;
        }
    }

    public boolean alterar (OrdemServico objeto) {
        try {

            ContentValues valores = new ContentValues();

            valores.put("id_servicos", objeto.getId_os());
            valores.put("nome_servicos", objeto.getNome_servico());
            valores.put("valor_servicos", objeto.getValor_servico());
            valores.put("observacao_servicos", objeto.getObservacao_servico());
            valores.put("status_servicos", objeto.getStatus_servico());

            String[] parametros= new String[1];
            parametros[0] = String.valueOf(objeto.getId_os());

            conexao.update(Tabelas.TB_OS, valores, "id_servicos = ? ", parametros );
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;
        }
    }

    public boolean excluir (OrdemServico objeto) {
        try {

            String[] parametros= new String[1];
            parametros[0] = String.valueOf(objeto.getId_os());

            conexao.delete(Tabelas.TB_OS, "id_servicos = ?", parametros );
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;
        }
    }


    public ArrayList<OrdemServico> lista () {

        ArrayList<OrdemServico> listagem = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_OS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.moveToFirst()) {
                OrdemServico objeto;
                do {
                    objeto = new OrdemServico();
                    objeto.setId_os(resultado.getInt(resultado.getColumnIndexOrThrow("id_servicos")));
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

