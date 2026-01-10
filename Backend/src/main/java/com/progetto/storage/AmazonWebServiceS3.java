package com.progetto.storage;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.progetto.exception.StorageException;
import com.progetto.interfaces.ImageStorageSaver;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Repository
class AmazonWebServiceS3 implements ImageStorageSaver {
    
    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    
    @Autowired
    private S3Client s3Client;

    @Override
    public String saveImage(MultipartFile file) throws StorageException { 
        String uniqueName = generateUniqueImageName();

        PutObjectRequest uploadRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(uniqueName)
        .contentType(file.getContentType())
        .build();
            
		try {
			s3Client.putObject(uploadRequest, RequestBody.fromBytes(file.getBytes()));
		} catch (AwsServiceException | SdkClientException | IOException e) {
			throw new StorageException();
		}
 
        return uniqueName;  
    }

    private String generateUniqueImageName() {
        return UUID.randomUUID().toString();
    }
}
