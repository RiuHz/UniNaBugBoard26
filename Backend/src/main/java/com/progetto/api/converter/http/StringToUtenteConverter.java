package com.progetto.api.converter.http;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.progetto.models.Utente;

@Component
public class StringToUtenteConverter implements Converter<String, Utente> {

    @Override
    public Utente convert(String id) {
        if (id == null || id.isBlank())
            return null;

        Utente utente = new Utente();
        utente.setId(id);

        return utente;
    }
}
