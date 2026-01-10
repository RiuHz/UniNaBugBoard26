package com.progetto.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

@Embeddable
public class Utente {
    private String id;
    @Transient
    private String nome;
    @Transient  
    private String cognome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
