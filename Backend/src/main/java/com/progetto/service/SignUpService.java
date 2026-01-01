package com.progetto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.model.RichiestaRegistrazione;

import com.progetto.exception.AuthException;
import com.progetto.interfaces.UserRegistration;

@Service
public class SignUpService {

    @Autowired
    public UserRegistration amazonWebServiceCognito;

    public void registraUtente(RichiestaRegistrazione utente) throws AuthException {
        amazonWebServiceCognito.registraUtente(utente);
    }
    
}
