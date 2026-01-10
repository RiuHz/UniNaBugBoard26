package com.progetto.models;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.progetto.api.converter.database.ImmagineConverter;
import com.progetto.api.converter.database.UtenteConverter;
import com.progetto.enums.issue.Priorita;
import com.progetto.enums.issue.Stato;
import com.progetto.enums.issue.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JsonPropertyOrder({"id", "utente", "titolo", "descrizione", "tipo", "priorita", "stato", "allegato"})
@Table(name = "Issue")
@Entity(name = "Issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    

    private String titolo;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "tipo",
        columnDefinition = "tipo"
    )
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "priorita",
        columnDefinition = "priorita"
    )
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Priorita priorita;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "stato",
        columnDefinition = "stato"
    )
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Stato stato;


    @Column(name = "allegato")
    @Convert(converter = ImmagineConverter.class)
    @JsonIgnore
    private Immagine allegato;

    @Column(name = "utente")
    @Convert(converter = UtenteConverter.class)
    private Utente utente;

    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
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

    public Utente getUtente() {
        return utente; 
    }

    public void setUtente(Utente utente) {
        this.utente = utente; 
    }

    public Immagine getAllegato() {
        return allegato;
    }

    public void setAllegato(Immagine allegato) {
        this.allegato = allegato;
    }

    @JsonProperty("allegato")
    public String getUrlAllegto() {
        return allegato != null ? allegato.getUrl() : null;
    }
}
