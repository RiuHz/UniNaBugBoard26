package com.progetto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.progetto.models.Utente;

@Component
public class StringToUtenteConverter implements Converter<String, Utente> {

    @Override
    public Utente convert(String source) {
        if (source == null || source.isBlank()) return null;

        Utente u = new Utente();
        u.setId(source);
        return u;
    }
}
