package com.example.lucaskaferchamados.model;

public class OrdemServico {

    private int id_os;
    private int id_usuarios;
    private int id_servicos;
    private String nome_servico;
    private String status_servico;
    private Float valor_servico;
    private String observacao_servico;

    public int getId_os() {
        return id_os;
    }

    public void setId_os(int id_os) {
        this.id_os = id_os;
    }

    public int getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public int getId_servicos() {
        return id_servicos;
    }

    public void setId_servicos(int id_servicos) {
        this.id_servicos = id_servicos;
    }

    public String getNome_servico() {
        return nome_servico;
    }

    public void setNome_servico(String nome_servico) {
        this.nome_servico = nome_servico;
    }

    public String getStatus_servico() {
        return status_servico;
    }

    public void setStatus_servico(String status_servico) {
        this.status_servico = status_servico;
    }

    public Float getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(Float valor_servico) {
        this.valor_servico = valor_servico;
    }

    public String getObservacao_servico() {
        return observacao_servico;
    }

    public void setObservacao_servico(String observacao_servico) {
        this.observacao_servico = observacao_servico;
    }

}
