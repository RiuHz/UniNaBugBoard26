package com.progetto.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.progetto.enums.Priorita;
import com.progetto.enums.Stato; 
import com.progetto.enums.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// Definisco l'ordine delle proprietà dell' oggetto JSON restituito al front-end
@JsonPropertyOrder({"id", "userid", "titolo", "descrizione", "tipo", "priorita", "stato", "allegato"})
// Segnalo che questa classe rappresenta una tabella del database
@Entity 
// Nome della tabella nel database
@Table(name = "issue")
public class Issue {
    @Id // Chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userid; // Se metto userId, lo traduce come underscore (da capire come fare)
    private String titolo;
    private String descrizione;

    /* Necessari altrimenti vengono trattati come tipi numerici gli enum scritti */
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Enumerated(EnumType.STRING)
    private Priorita priorita;
    @Enumerated(EnumType.STRING)
    private Stato stato;
    
    private String allegato;

    public int getId() {
        return id;
    }

    public String getuserid() {
        return userid;
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

    public void setuserid(String userid) {
        this.userid = userid;
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
