package com.progetto.model.issues;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "userid", "titolo", "descrizione", "tipo", "priorita", "stato", "allegato"})
@Table(name = "Issue")
@Entity(name = "Issue")
public class StorageIssue extends Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private String allegato;
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getAllegato(){
        return allegato;
    }

    public void setAllegato(String allegato) {
        this.allegato = allegato;
    }
    
    public static StorageIssue fromUserIssue(UserIssue userIssue) {
    	StorageIssue storageIssue = new StorageIssue();
    	
        storageIssue.setUserid(userIssue.getUserId());
    	storageIssue.setTitolo(userIssue.getTitolo());
    	storageIssue.setDescrizione(userIssue.getDescrizione());
    	storageIssue.setTipo(userIssue.getTipo());
    	storageIssue.setPriorita(userIssue.getPriorita());
    	storageIssue.setStato(userIssue.getStato());
    	
    	return storageIssue;
    }
    
}
