package com.progetto.model.issues;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JsonPropertyOrder({"id", "userInfo", "titolo", "descrizione", "tipo", "priorita", "stato", "allegato"})
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
    	
        if (userIssue.getUserInfo() != null) {
            storageIssue.setUserInfo(userIssue.getUserInfo());
        }
    	storageIssue.setTitolo(userIssue.getTitolo());
    	storageIssue.setDescrizione(userIssue.getDescrizione());
    	storageIssue.setTipo(userIssue.getTipo());
    	storageIssue.setPriorita(userIssue.getPriorita());
    	storageIssue.setStato(userIssue.getStato());
    	
    	return storageIssue;
    }
    
}
