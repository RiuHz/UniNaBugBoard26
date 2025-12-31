package com.progetto.storage;

import com.progetto.interfaces.ImageStorageSaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.util.UUID;

@Repository
class AmazonWebServiceS3 implements ImageStorageSaver {
    
    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    
    private S3Client s3Client;

    @Autowired
    public AmazonWebServiceS3(S3Client s3Client) {
        this.s3Client = s3Client;
    }
    
    public String saveImage(MultipartFile file) { 
        String uniqueName = generateUniqueImageName(file.getOriginalFilename());

        PutObjectRequest uploadRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(uniqueName)
            .contentType(file.getContentType())
            .build();
            
		try {
			s3Client.putObject(uploadRequest, RequestBody.fromBytes(file.getBytes()));
		} catch (AwsServiceException | SdkClientException | IOException e) {
			return null;
		}
 
        return uniqueName;  
    };

    private String generateUniqueImageName(String fileName) {
        return UUID.randomUUID().toString() + "_" + fileName;
    }
}

/*     // Funzione per caricare un immagine
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = s3Service.uploadFile(file);
            return ResponseEntity.ok("File caricato con successo! Nome salvato: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Errore durante l'upload: " + e.getMessage());
        }
    }

    
    @GetMapping("/{url}")
    public byte[] getImageByUrl(@PathVariable String url) {
        return imageService.recuperaImmagineDaUrl(url);
    } */
