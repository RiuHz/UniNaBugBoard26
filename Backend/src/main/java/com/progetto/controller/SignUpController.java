package com.progetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.model.RichiestaRegistrazione;
import com.progetto.service.SignUpService;

@RestController
@RequestMapping("/sign-up")
public class SignUpController{

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody RichiestaRegistrazione utente) {
        try {
            signUpService.registraUtente(utente);
        } catch AuthException {
            return ResponseEntity.internalServerError();
        }

        return ResponseEntity.ok("User created");
    }

}
