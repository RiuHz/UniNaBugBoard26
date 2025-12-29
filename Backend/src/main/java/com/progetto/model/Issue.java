package com.progetto.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.progetto.enums.*;

// Definisco l'ordine delle proprietà dell' oggetto JSON restituito al front-end
@JsonPropertyOrder({"id", "userId", "titolo", "descrizione", "tipo", "priorita", "stato", "allegato"}) 
public class Issue {
    private int id;
    private String userId;
    private String titolo;
    private String descrizione;
    private Tipo tipo;
    private Priorita priorita;
    private Stato stato;
    private String allegato;

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Tipo getTipo(){
        return tipo;
    }

    public Priorita getPriorita(){
        return priorita;
    }

    public Stato getStato(){
        return stato;
    }

    public String getAllegato(){
        return allegato;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setPriorita(Priorita priorita) {
        this.priorita = priorita;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public void setAllegato(String allegato) {
        this.allegato = allegato;
    }

    

}
