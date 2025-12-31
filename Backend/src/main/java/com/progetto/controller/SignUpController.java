package com.progetto.controller;

import com.progetto.model.SignUpRequest;
import com.progetto.service.SignUpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class SignUpController{

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public void createUser(@RequestBody SignUpRequest utente) {
        signUpService.registraUtente(utente);
    }

}
