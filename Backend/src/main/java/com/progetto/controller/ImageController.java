package com.progetto.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.progetto.service.ImageService;
import com.progetto.service.S3Service;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    
    @Autowired
    private  S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @GetMapping("/{url}")
    public byte[] getImageByUrl(@PathVariable String url) {
        return imageService.recuperaImmagineDaUrl(url);
    }

    @GetMapping("/filename/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) {
        byte[] data = s3Service.downloadFile(fileName);

        MediaType mediaType = MediaType.IMAGE_JPEG; 

        return ResponseEntity.ok()
                .contentType(mediaType) 
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(data);
    }

    // Funzione per caricare un immagine
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = s3Service.uploadFile(file);
            return ResponseEntity.ok("File caricato con successo! Nome salvato: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Errore durante l'upload: " + e.getMessage());
        }
    }
}

