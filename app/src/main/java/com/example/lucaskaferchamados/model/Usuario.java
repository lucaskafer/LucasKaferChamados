package com.example.lucaskaferchamados.model;


public class Usuario {

    private int id;
    private String login;
    private String email;
    private String telefone;
    private String senha;

    public int getId () { return id; }

    public void setId (int id) {
        this.id = id;
    }

    public String getLogin () {
        return login;
    }

    public void setLogin (String login) {
        this.login = login;
    }

    public String getSenha () { return senha; }

    public void setSenha (String senha) {
        this.senha = senha;
    }

    public String getEmail () { return email;}

    public void setEmail (String email) {
        this.email = email;
    }

    public String getTelefone () {
        return telefone;
    }

    public void setTelefone (String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString(){ return this.login;}

}
