package com.progetto.interfaces;

import com.progetto.model.RichiestaRegistrazione;
import com.progetto.exception.AuthException;

public interface UserRegistration {
    public String registraUtente(RichiestaRegistrazione utente) throws AuthException;
}
