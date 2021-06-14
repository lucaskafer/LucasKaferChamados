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
            //-----------------------------------------------
            StringBuffer sqlServico = new StringBuffer();
            sqlServico.append(" CREATE TABLE IF NOT EXISTS ");
            sqlServico.append(Tabelas.TB_SERVICOS);
            sqlServico.append("(");
            sqlServico.append("id_servicos INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlServico.append("nome_servicos VARCHAR(30) NULL, ");
            sqlServico.append("valor_servicos NUMERIC(10,5)NULL, ");
            sqlServico.append("status_servicos VARCHAR(30) NULL,");
            sqlServico.append("observacao_servicos VARCHAR(5000) NULL,");
            sqlServico.append("date_servicos DATE NULL");
            //sqlSerPadrao.append("FOREIGN KEY(id) REFERENCES "+ Tabelas.TB_SERVICOS +"(id)");
            sqlServico.append(")");
            db.execSQL(sqlServico.toString());

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
            //-----------------------------------------------------------------------


            StringBuffer sqlUsuarios = new StringBuffer();
            sqlUsuarios.append(" CREATE TABLE IF NOT EXISTS ");
            sqlUsuarios.append(Tabelas.TB_USUARIOS);
            sqlUsuarios.append("(");
            sqlUsuarios.append("id_user INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlUsuarios.append("login_user VARCHAR(30) NOT NULL, ");
            sqlUsuarios.append("senha_user VARCHAR(100) NOT NULL, ");
            sqlUsuarios.append("email_user VARCHAR(100) NULL, ");
            sqlUsuarios.append("telefone_user VARCHAR(20) NULL ");
            sqlUsuarios.append(")");
            db.execSQL(sqlUsuarios.toString());

            StringBuilder sqlUserAdmin = new StringBuilder();
            sqlUserAdmin.append("INSERT INTO ");
            sqlUserAdmin.append(Tabelas.TB_USUARIOS);
            sqlUserAdmin.append("(login_user , senha_user ) VALUES('admin', '" + Globais.md5("admin") +"')");
            db.execSQL(sqlUserAdmin.toString());


            //---------------------------------------------

            StringBuffer sqlOrdemServico = new StringBuffer();
            sqlOrdemServico.append(" CREATE TABLE IF NOT EXISTS ");
            sqlOrdemServico.append(Tabelas.TB_OS);
            sqlOrdemServico.append("(");
            // id_user
            sqlOrdemServico.append("id_user INTEGER NULL, ");
            // id_servico
            sqlOrdemServico.append("id_servicos INTEGER NULL, ");

            sqlOrdemServico.append("id_os INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlOrdemServico.append("nome_servicos VARCHAR(30) NULL, ");
            sqlOrdemServico.append("valor_servicos VARCHAR(30) NULL, ");
            sqlOrdemServico.append("status_servicos VARCHAR(30) NULL, ");
            sqlOrdemServico.append("observacao_servicos VARCHAR(5000) NULL, ");
            sqlOrdemServico.append("date_servicos DATE NULL, ");
            sqlOrdemServico.append("FOREIGN KEY(id_user) REFERENCES "+ Tabelas.TB_USUARIOS +"(id_user), ");
            sqlOrdemServico.append("FOREIGN KEY(id_servicos) REFERENCES "+ Tabelas.TB_SERVICOS +"(id_servicos) ");
            sqlOrdemServico.append(")");
            db.execSQL(sqlOrdemServico.toString());

            StringBuilder sqlOrdemServicopadrao = new StringBuilder();
            sqlOrdemServicopadrao.append("INSERT INTO ");
            sqlOrdemServicopadrao.append(Tabelas.TB_OS);
            sqlOrdemServicopadrao.append("(nome_servicos , valor_servicos, observacao_servicos, date_servicos ) " +
                           "VALUES " + "('Serv Teste', '50,00', 'intalação impressora', '2021')");
            db.execSQL(sqlOrdemServicopadrao.toString());



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

