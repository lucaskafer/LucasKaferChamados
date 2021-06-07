package com.example.lucaskaferchamados.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lucaskaferchamados.config.DadosOpenHelper;
import com.example.lucaskaferchamados.config.Globais;
import com.example.lucaskaferchamados.config.Tabelas;
import com.example.lucaskaferchamados.model.Usuario;

import java.util.ArrayList;

public class UsuarioController {

    Context context;
    SQLiteDatabase conexao;

    public UsuarioController (Context context) {
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;

    }
    // metodos ( buscar, listar, alterar, incluir, excluir)

    public Usuario login (String usuario, String senha) {
        try {
            Usuario objeto = null;

            senha = Globais.md5(senha);

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_USUARIOS);
            sql.append(" WHERE login_user = '"+ usuario +"'");
            sql.append(" AND senha_user = '"+ senha +"'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.moveToNext()) {

                objeto = new Usuario();
                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id_user")));
                objeto.setLogin(resultado.getString(resultado.getColumnIndexOrThrow("login_user")));
                objeto.setSenha(resultado.getString(resultado.getColumnIndexOrThrow("senha_user")));
                objeto.setEmail(resultado.getString(resultado.getColumnIndexOrThrow("email_user")));
                objeto.setTelefone(resultado.getString(resultado.getColumnIndexOrThrow("telefone_user")));
            }

            return objeto;

        } catch (Exception ex) {
            Globais.exibirMensagens(context, ex.getMessage());
            return null;
        }
    }

    public Usuario buscar (int id) {
        try {
            Usuario objeto = null;

            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM ");
            sql.append(Tabelas.TB_USUARIOS);
            sql.append(" WHERE id_user = '" + id + "'");


            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if (resultado.getCount() > 0) {
                resultado.moveToFirst();
                objeto = new Usuario();
                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id_user")));
                objeto.setLogin(resultado.getString(resultado.getColumnIndexOrThrow("login_user")));
                objeto.setSenha(resultado.getString(resultado.getColumnIndexOrThrow("senha_user")));
                objeto.setEmail(resultado.getString(resultado.getColumnIndexOrThrow("email_user")));
                objeto.setTelefone(resultado.getString(resultado.getColumnIndexOrThrow("telefone_user")));
            }

            return objeto;

        } catch (Exception ex) {
            Globais.exibirMensagens(context, ex.getMessage());
            return null;
        }
    }

    public boolean incluir (Usuario objeto) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("login_user", objeto.getLogin());
            valores.put("senha_user", objeto.getSenha());
            valores.put("email_user", objeto.getEmail());
            valores.put("telefone_user", objeto.getTelefone());

            conexao.insertOrThrow(Tabelas.TB_USUARIOS, null, valores);
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;
        }

    }

    public boolean alterar (Usuario objeto) {
        try {

            ContentValues valores = new ContentValues();
            valores.put("login_user", objeto.getLogin());
            valores.put("senha_user", objeto.getSenha());
            valores.put("email_user", objeto.getEmail());
            valores.put("telefone_user", objeto.getTelefone());

            String[] parametros= new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.update(Tabelas.TB_USUARIOS, valores, "id_user = ? ", parametros );
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;

        }

    }

    public boolean excluir (Usuario objeto) {
        try {

            String[] parametros= new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.delete(Tabelas.TB_USUARIOS, "id_user = ?", parametros );
            return true;

        }
        catch (Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());
            return false;

        }

    }

    public ArrayList<Usuario> lista () {

        ArrayList<Usuario> listagem = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_USUARIOS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.moveToFirst()) {

                Usuario objeto;
                do {
                    objeto = new Usuario();
                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id_user")));
                    objeto.setLogin(resultado.getString(resultado.getColumnIndexOrThrow("login_user")));
                    objeto.setEmail(resultado.getString(resultado.getColumnIndexOrThrow("email_user")));
                    objeto.setTelefone(resultado.getString(resultado.getColumnIndexOrThrow("telefone_user")));

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
