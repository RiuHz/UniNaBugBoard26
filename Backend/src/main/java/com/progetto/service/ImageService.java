package com.progetto.service;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageStorage amazonWebServiceS3Repository;

    public byte[] recuperaImmagineDaUrl(String url) {
        return amazonWebServiceS3Repository.retrieveImage(url);
    }
}
