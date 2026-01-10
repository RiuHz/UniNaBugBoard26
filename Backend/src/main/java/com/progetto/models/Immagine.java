package com.progetto.models;

import org.springframework.web.multipart.MultipartFile;

public class Immagine {
    private String url;
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
