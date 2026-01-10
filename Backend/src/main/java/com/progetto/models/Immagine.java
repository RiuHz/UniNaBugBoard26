package com.progetto.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

@Embeddable
public class Immagine {
    private String url;
    @Transient
    private MultipartFile file;

    public String getUrl() {
        return url;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
