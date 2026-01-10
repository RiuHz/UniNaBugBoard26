package com.progetto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.progetto.models.Immagine;

@Component
public class MultipartFileToImmagineConverter
        implements Converter<MultipartFile, Immagine> {

    @Override
    public Immagine convert(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;

        Immagine img = new Immagine();
        img.setFile(file);   // solo il file
        return img;
    }
}

