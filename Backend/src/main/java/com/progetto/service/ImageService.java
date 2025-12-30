package com.progetto.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.interfaces.ImageStorage;


@Service
public class ImageService {

    @Autowired
    private ImageStorage amazonWebServiceS3Repository;

    public byte[] recuperaImmagineDaUrl(String url) {
        return amazonWebServiceS3Repository.retrieveImage(url);
    }
}
