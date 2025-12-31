package com.progetto.model.issues;

import com.progetto.enums.PrioritaIssue;
import com.progetto.enums.StatoIssue; 
import com.progetto.enums.TipoIssue;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

abstract class Issue {
    private String userid;
    private String titolo;
    private String descrizione;
    
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
    
}
