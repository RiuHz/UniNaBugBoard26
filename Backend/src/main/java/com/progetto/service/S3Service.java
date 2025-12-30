package com.progetto.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    // Metodo per recuperare l' immagine
    public byte[] downloadFile(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName) 
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return objectBytes.asByteArray();
    }

    // Metodo per caricare l' immagine
    public String uploadFile(MultipartFile file) throws IOException {

    //Genero un nome univoco per evitare di sovrascrivere file esistenti
    String originalFileName = file.getOriginalFilename();
    String keyName = UUID.randomUUID().toString() + "_" + originalFileName;

    // Creo la richiesta di upload
    PutObjectRequest putOb = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(keyName)
            .contentType(file.getContentType())
            .build();

    // Invio il file ad S3
    s3Client.putObject(putOb, RequestBody.fromBytes(file.getBytes()));
    
    //s3Client.putObject(putOb, RequestBody.fromInputStream(file.getInputStream(), file.getSize())); //Alternativa non usa ram

    return keyName;
    
    }
}
