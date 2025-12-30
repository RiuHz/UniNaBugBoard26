package com.progetto.repository;
import org.springframework.stereotype.Repository;

import com.progetto.interfaces.ImageStorage;

@Repository
class AmazonWebServiceS3 implements ImageSaver {
    
    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }
    public String saveImage(MultipartFile file) {
        String uniqueName = generateUniqueImageName(file.getOriginalFilename());

        PutObjectRequest uploadRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(uniqueName)
            .contentType(file.getContentType())
            .build();
            
        s3Client.putObject(uploadRequest, RequestBody.fromBytes(file.getBytes()));
 
        return uniqueName;  
    };

    private generateUniqueImageName(String fileName) {
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
