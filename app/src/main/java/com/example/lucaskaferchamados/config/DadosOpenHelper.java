package com.example.lucaskaferchamados.config;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DadosOpenHelper extends SQLiteOpenHelper
{

    static final int VERSION = 1;
    static final String NM_BANCO = "banco";
    Context context;

    public DadosOpenHelper (Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }
    @Override
    public void onCreate (SQLiteDatabase db) {
        try {

            StringBuffer sqlFinanceiro = new StringBuffer();
            sqlFinanceiro.append(" CREATE TABLE IF NOT EXISTS ");
            sqlFinanceiro.append(Tabelas.TB_SERVICOS);
            sqlFinanceiro.append("(");
            sqlFinanceiro.append("id_servicos INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlFinanceiro.append("nome_servicos VARCHAR(30) NULL, ");
            sqlFinanceiro.append("valor_servicos VARCHAR(30) NULL, ");
            sqlFinanceiro.append("status_servicos VARCHAR(30) NULL,");
            sqlFinanceiro.append("observacao_servicos VARCHAR(5000) NULL,");
            sqlFinanceiro.append("date_servicos DATE NULL");
            //sqlSerPadrao.append("FOREIGN KEY(id) REFERENCES "+ Tabelas.TB_SERVICOS +"(id)");
            sqlFinanceiro.append(")");
            db.execSQL(sqlFinanceiro.toString());

            StringBuilder sqlSerPadrao = new StringBuilder();
            sqlSerPadrao.append("INSERT INTO ");
            sqlSerPadrao.append(Tabelas.TB_SERVICOS);
            sqlSerPadrao.append("(nome_servicos, valor_servicos, status_servicos, observacao_servicos ) VALUES ('Servico_Ativo', '50,00', 'Ativo', 'intalação impressora')");
            db.execSQL(sqlSerPadrao.toString());
            StringBuilder sqlSerPadrao2 = new StringBuilder();
            sqlSerPadrao2.append("INSERT INTO ");
            sqlSerPadrao2.append(Tabelas.TB_SERVICOS);
            sqlSerPadrao2.append("(nome_servicos, valor_servicos, status_servicos, observacao_servicos ) VALUES ('Servico_Cancelado', '0,00', 'Inativo', 'Cliente cancelou')");
            db.execSQL(sqlSerPadrao2.toString());



            StringBuffer sqlUsuarios = new StringBuffer();
            sqlUsuarios.append(" CREATE TABLE IF NOT EXISTS ");
            sqlUsuarios.append(Tabelas.TB_USUARIOS);
            sqlUsuarios.append("(");
            sqlUsuarios.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlUsuarios.append("login VARCHAR(30) NOT NULL, ");
            sqlUsuarios.append("senha VARCHAR(100) NOT NULL, ");
            sqlUsuarios.append("email VARCHAR(100) NULL, ");
            sqlUsuarios.append("telefone VARCHAR(20) NULL ");
            sqlUsuarios.append(")");
            db.execSQL(sqlUsuarios.toString());

            StringBuilder sqlUserAdmin = new StringBuilder();
            sqlUserAdmin.append("INSERT INTO ");
            sqlUserAdmin.append(Tabelas.TB_USUARIOS);
            sqlUserAdmin.append("(login, senha) VALUES('admin', '" + Globais.md5("admin") +"')");
            db.execSQL(sqlUserAdmin.toString());




        } catch (Exception ex) {
            Globais.exibirMensagens(context, ex.getMessage());
        }
    }



    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {

        try {

            if (oldVersion == 1 && newVersion == 2) {
                StringBuilder sqlUpdate = new StringBuilder();

                sqlUpdate.append(" ALTER TABLE usuarios ADD COLUMN email VARCHAR(100) ");
                db.execSQL(sqlUpdate.toString());

                sqlUpdate = new StringBuilder();
                sqlUpdate.append(" ALTER TABLE usuarios ADD COLUMN telefone VARCHAR(20) ");
                db.execSQL(sqlUpdate.toString());

            }
        }
        catch(Exception ex)
        {
            Globais.exibirMensagens(context, ex.getMessage());

        }
    }
}
