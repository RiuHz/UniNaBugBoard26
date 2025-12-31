package com.progetto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.interfaces.UserRegistration;
import com.progetto.model.SignUpRequest;

@Service
public class SignUpService {

    @Autowired
    public UserRegistration amazonWebServiceCognito;

    public String registraUtente(SignUpRequest utente) {
        return amazonWebServiceCognito.registraUtente(utente);
    }
    
}