package com.progetto.controller;

import com.progetto.service.CognitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-up")
public class AuthController {

    @Autowired
    private CognitoService cognitoService;

    @PostMapping("")
    public String register(@RequestParam SignUpRequest utente) {
        return cognitoService.registraUtente(utente);
    }
}
