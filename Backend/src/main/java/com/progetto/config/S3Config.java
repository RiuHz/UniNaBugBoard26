package com.progetto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.access.key}")
    private String accessKeyId;

    @Value("${aws.secret.key}")
    private String secretAccessKey;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Bean
    public S3Client s3Client(){

        // Creo le credenziali AWS, andando a leggere dal file application.properties
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);

        // Configuro il client S3 con le credenziali create
        return S3Client.builder()
                .region(Region.EU_SOUTH_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}
