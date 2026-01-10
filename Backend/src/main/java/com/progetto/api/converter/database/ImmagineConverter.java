package com.progetto.api.converter.database;

import com.progetto.models.Immagine;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class ImmagineConverter implements AttributeConverter<Immagine, String> {

    @Override
    public String convertToDatabaseColumn(Immagine immagine) {
        return immagine != null ? immagine.getUrl() : null;
    }

    @Override
    public Immagine convertToEntityAttribute(String url) {
        Immagine immagine = new Immagine();
        immagine.setUrl(url);
        
        return immagine;
    }
}
