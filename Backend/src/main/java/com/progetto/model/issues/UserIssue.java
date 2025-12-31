package com.progetto.model.issues;

import org.springframework.web.multipart.MultipartFile;

public class UserIssue {
	private String userid;
    private String titolo;
    private String descrizione;
	private Tipo tipo;
	private Priorita priorita;
	private Stato stato;
	private MultipartFile immagine;

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
	
	public void setImmagine(MultipartFile immagine) {
		this.immagine = immagine;
	}
	
	public MultipartFile getImmagine() {
		return immagine;
	}
}
