package com.progetto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.model.RichiestaRegistrazione;
import com.progetto.interfaces.UserRegistration;

@Service
public class SignUpService {

    @Autowired
    public UserRegistration amazonWebServiceCognito;

    public String registraUtente(RichiestaRegistrazione utente) {
        return amazonWebServiceCognito.registraUtente(utente);
    }
    
}
