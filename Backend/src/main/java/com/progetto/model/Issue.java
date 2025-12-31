package com.progetto.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.progetto.enums.PrioritaIssue;
import com.progetto.enums.StatoIssue; 
import com.progetto.enums.TipoIssue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JsonPropertyOrder({"id", "userid", "titolo", "descrizione", "tipo", "priorita", "stato", "allegato"})
@Table(name = "issue")
@Entity 
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userid;
    private String titolo;
    private String descrizione;
    private String allegato;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", columnDefinition = "tipo")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private TipoIssue tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "priorita", columnDefinition = "priorita")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private PrioritaIssue priorita;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", columnDefinition = "stato")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private StatoIssue stato;

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

    public TipoIssue getTipo(){
        return tipo;
    }

    public PrioritaIssue getPriorita(){
        return priorita;
    }

    public StatoIssue getStato(){
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

    public void setTipo(TipoIssue tipo) {
        this.tipo = tipo;
    }

    public void setPriorita(PrioritaIssue priorita) {
        this.priorita = priorita;
    }

    public void setStato(StatoIssue stato) {
        this.stato = stato;
    }

    public void setAllegato(String allegato) {
        this.allegato = allegato;
    }
}
