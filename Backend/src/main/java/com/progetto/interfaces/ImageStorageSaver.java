package com.progetto.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.progetto.exception.StorageException;

public interface ImageStorageSaver {
    String saveImage(MultipartFile imageData) throws StorageException;
}
