package com.progetto.model;

import com.progetto.enums.Priorita;
import com.progetto.enums.Stato; 
import com.progetto.enums.Tipo;

import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "priorita", columnDefinition = "priorita")
    private Priorita priorita;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", columnDefinition = "stato")
    private Stato stato;
}
