package com.br.persistenciarealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by asouzasa on 11/07/2017.
 */

public class Livro extends RealmObject {

    @PrimaryKey
    int id;
    String autor;
    String nome;
    int ano;

    public Livro(){ }

    public Livro(int id, String autor, String nome, int ano) {
        this.id = id;
        this.autor = autor;
        this.nome = nome;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

}
