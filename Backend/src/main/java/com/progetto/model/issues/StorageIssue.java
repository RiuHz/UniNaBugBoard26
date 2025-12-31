package com.progetto.model.issues;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@JsonPropertyOrder({"id", "userid", "titolo", "descrizione", "tipo", "priorita", "stato", "allegato"})
@Table(name = "Issue")
@Entity(name = "Issue")
public class StorageIssue extends Issue {
    private String allegato;
    
    public String getallegato(){
        return allegato;
    }

    public void setallegato(String allegato) {
        this.allegato = allegato;
    }
    
    public static StorageIssue fromUserIssue(UserIssue userIssue) {
    	StorageIssue storageIssue = new StorageIssue();
    	
    	storageIssue.setTitolo(userIssue.getTitolo());
    	storageIssue.setDescrizione(userIssue.getDescrizione());
    	storageIssue.setTipo(userIssue.getTipo());
    	storageIssue.setPriorita(userIssue.getPriorita());
    	storageIssue.setStato(userIssue.getStato());
    	
    	return storageIssue;
    }
    
}
