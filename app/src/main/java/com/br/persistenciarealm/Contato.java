package com.br.persistenciarealm;

import io.realm.RealmObject;

/**
 * Created by asouzasa on 14/07/2017.
 */

public class Contato extends RealmObject {
    private String nome;
    private byte[] foto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
