package com.progetto.api.converter.database;

import com.progetto.models.Utente;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class UtenteConverter implements AttributeConverter<Utente, String> {

    @Override
    public String convertToDatabaseColumn(Utente utente) {
        return utente != null ? utente.getId() : null;
    }

    @Override
    public Utente convertToEntityAttribute(String id) {
        if (id == null)
            return null;

        Utente utente = new Utente();
        utente.setId(id);
        
        return utente;
    }
}
