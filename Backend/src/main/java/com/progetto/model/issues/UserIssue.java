package com.progetto.model.issues;

import org.springframework.web.multipart.MultipartFile;

public class UserIssue extends Issue {
	private MultipartFile allegato;
	
	public void setAllegato(MultipartFile allegato) {
		this.allegato = allegato;
	}

	public MultipartFile getAllegato() {
		return allegato;
	}
}
