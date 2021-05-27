package com.example.lucaskaferchamados.model;

public class StatusServicos {

        private int id;
        private String tipo;

        public StatusServicos(int id, String tipo){
            this.id = id;
            this.tipo = tipo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return tipo;
        }

        public void setNome(String nome) {
            this.tipo = nome;
        }

        @Override
        public String toString(){
            return this.tipo;
        }
    }

