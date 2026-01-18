package com.progetto.interfaces;

import com.progetto.exception.AuthException;
import com.progetto.models.RichiestaRegistrazione;

public interface UserRegistration {
    String registraUtente(RichiestaRegistrazione utente) throws AuthException;
}
