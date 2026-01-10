package com.progetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.service.SignUpService;
import com.progetto.exception.AuthException;
import com.progetto.models.RichiestaRegistrazione;

@RestController
@RequestMapping("/sign-up")
public class SignUpController{

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody RichiestaRegistrazione utente) {
        try {
            signUpService.registraUtente(utente);
        } catch(AuthException errror) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok("User created");
    }

}
