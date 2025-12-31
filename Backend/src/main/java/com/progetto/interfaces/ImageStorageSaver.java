package com.progetto.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageSaver {
    String saveImage(MultipartFile imageData);
}
