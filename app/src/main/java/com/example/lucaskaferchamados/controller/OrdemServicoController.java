package com.example.lucaskaferchamados.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lucaskaferchamados.config.DadosOpenHelper;

public class OrdemServicoController {

    Context context;
    SQLiteDatabase conexao;

    public OrdemServicoController (Context context) {
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;

    }
// metodos ( buscar, listar, alterar, incluir, excluir)

}