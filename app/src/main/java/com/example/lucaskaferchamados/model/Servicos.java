package com.example.lucaskaferchamados.model;

public class Servicos {

    private int id_servico;
    private String nome_servico;
    private String status_servico;
    private String valor_servico;
    private String observacao_servico;

    public String getObservacao_servico () { return observacao_servico; }

    public void setObservacao_servico (String observacao_servico) {  this.observacao_servico = observacao_servico;}

    public int getId_servico () { return id_servico;}

    public void setId_servico (int id_servico) { this.id_servico = id_servico; }

    public String getNome_servico () {
        return nome_servico;
    }

    public void setNome_servico (String nome) {
        this.nome_servico = nome;
    }

    public String getValor_servico () { return valor_servico; }

    public void setValor_servico (String valor) {
        this.valor_servico = valor;
    }

    public String getStatus_servico () {
        return status_servico;
    }

    public void setStatus_servico (String status) {
        this.status_servico = status;
    }

    @Override
    public String toString(){ return this.nome_servico;}
}


