package com.progetto.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progetto.api.service.SignUpService;
import com.progetto.enums.utente.RuoloUtente;
import com.progetto.exception.AuthException;
import com.progetto.models.RichiestaRegistrazione;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignUpController.class)
public class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignUpService signUpService;

    // Serve a convertire gli oggetti Java in JSON per la richiesta HTTP
    @Autowired
    private ObjectMapper objectMapper; 

    @Test
    public void test01() throws Exception {

        //ARRANGE
        RichiestaRegistrazione nuovoUtente = new RichiestaRegistrazione();
        nuovoUtente.setEmail("mario@email.com");
        nuovoUtente.setPassword("Password123!");
        nuovoUtente.setNome("Mario");
        nuovoUtente.setCognome("Rossi");
        nuovoUtente.setRuolo(RuoloUtente.ADMIN);


        // Poiché il metodo del service è 'void', usiamo doNothing().
        doNothing().when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente))) // Converte l'oggetto in JSON string
                
        // ASSERT
                .andExpect(status().isOk()) 
                .andExpect(content().string("User created"));
    }

    @Test
    public void test02() throws Exception {
        // ARRANGE
        RichiestaRegistrazione nuovoUtente = new RichiestaRegistrazione();
        nuovoUtente.setEmail("luca@email.com");
        nuovoUtente.setPassword("pass1"); // password che non rispetta i requisiti
        nuovoUtente.setNome("Luca");
        nuovoUtente.setCognome("Verdi");
        nuovoUtente.setRuolo(RuoloUtente.SVILUPPATORE);
        
        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) // Ci aspettiamo 400 Bad Request
                .andExpect(content().string("Utente non creato"));

    }

    
}