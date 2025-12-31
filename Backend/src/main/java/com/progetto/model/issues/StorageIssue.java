package com.progetto.model.issues;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@JsonPropertyOrder({"id", "userid", "titolo", "descrizione", "tipo", "priorita", "stato", "allegato"})
@Table(name = "issue")
@Entity
public class StorageIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String userid;
    private String titolo;
    private String descrizione;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", columnDefinition = "tipo")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "priorita", columnDefinition = "priorita")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Priorita priorita;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", columnDefinition = "stato")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Stato stato;

    private String allegato;
        
    public static StorageIssue fromUserIssue(UserIssue userIssue) {
    	StorageIssue storageIssue = new StorageIssue();
    	
    	storageIssue.setTitolo(userIssue.getTitolo());
    	storageIssue.setDescrizione(userIssue.getDescrizione());
    	storageIssue.setTipo(userIssue.getTipo());
    	storageIssue.setPriorita(userIssue.getPriorita());
    	storageIssue.setStato(userIssue.getStato());
    	
    	return storageIssue;
    }
    
    public String getallegato(){
        return allegato;
    }

    public void setallegato(String allegato) {
        this.allegato = allegato;
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
}
