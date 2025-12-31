package com.progetto.model.issues;

import org.springframework.web.multipart.MultipartFile;

public class UserIssue extends Issue {
	private MultipartFile immagine;
	
	public void setImmagine(MultipartFile immagine) {
		this.immagine = immagine;
	}
	
	public MultipartFile getImmagine() {
		return immagine;
	}
}
