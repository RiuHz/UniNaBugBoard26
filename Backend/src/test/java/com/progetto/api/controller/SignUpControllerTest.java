/* package com.progetto.api.controller;

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

        //ARRANGE (refactor da fare, possibile code smell long parameter list)
        RichiestaRegistrazione nuovoUtente = creaUtente("Admin@admin.com","AdminAdmin1!","Mario","Rossi", RuoloUtente.ADMIN);

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

        //ARRANGE
        RichiestaRegistrazione nuovoUtente = creaUtente("Sviluppatore1@gmail.it","Sviluppatore1!","Lucia","Verdi", RuoloUtente.SVILUPPATORE);

        doNothing().when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isOk()) 
                .andExpect(content().string("User created"));
    }

    @Test
    public void test03() throws Exception {
        // ARRANGE
        // campo email vuoto
        RichiestaRegistrazione nuovoUtente = creaUtente(null,"Sviluppatore2!","Marco","Bianchi", RuoloUtente.SVILUPPATORE);

        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) // Ci aspettiamo 400 Bad Request
                .andExpect(content().string("User not created"));

    }

    @Test
    public void test04() throws Exception {
        // ARRANGE
        // campo email malformato
        RichiestaRegistrazione nuovoUtente = creaUtente("aaa@","Sviluppatore2!","Marco","Bianchi", RuoloUtente.SVILUPPATORE);

        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) 
                .andExpect(content().string("User not created"));

    }

    @Test
    public void test05() throws Exception {
        // ARRANGE
        // campo email malformato
        RichiestaRegistrazione nuovoUtente = creaUtente("Sviluppatore2@gmail.it",null,"Marco","Bianchi", RuoloUtente.SVILUPPATORE);

        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) 
                .andExpect(content().string("User not created"));

    }

    @Test
    public void test06() throws Exception {
        // ARRANGE
        // password che non rispetta i requisiti
        RichiestaRegistrazione nuovoUtente = creaUtente("Sviluppatore2@gmail.it","passw","Marco","Bianchi", RuoloUtente.SVILUPPATORE);

        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) 
                .andExpect(content().string("User not created"));

    }

    @Test
    public void test07() throws Exception {
        // ARRANGE
        // campo nome vuoto
        RichiestaRegistrazione nuovoUtente = creaUtente("Sviluppatore2@gmail.it","Sviluppatore2!",null,"Bianchi", RuoloUtente.SVILUPPATORE);

        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) 
                .andExpect(content().string("User not created"));

    }

    @Test
    public void test08() throws Exception {
        // ARRANGE
        // campo nome malformato
        RichiestaRegistrazione nuovoUtente = creaUtente("Sviluppatore2@gmail.it","Sviluppatore2!","@@@","Bianchi", RuoloUtente.ADMIN);

        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) 
                .andExpect(content().string("User not created"));

    }

    @Test
    public void test09() throws Exception {
        // ARRANGE
        // campo cognome vuoto
        RichiestaRegistrazione nuovoUtente = creaUtente("Sviluppatore2@gmail.it","Sviluppatore2!","Mario",null, RuoloUtente.ADMIN);

        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) 
                .andExpect(content().string("User not created"));

    }

    @Test
    public void test10() throws Exception {
        // ARRANGE
        // campo cognome malformato
        RichiestaRegistrazione nuovoUtente = creaUtente("Sviluppatore2@gmail.it","Sviluppatore2!","Mario","###", RuoloUtente.ADMIN);

        doThrow(new AuthException())
                .when(signUpService).registraUtente(any(RichiestaRegistrazione.class));

        //ACT
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuovoUtente)))
                
        // ASSERT
                .andExpect(status().isBadRequest()) 
                .andExpect(content().string("User not created"));

    }


    private RichiestaRegistrazione creaUtente(String email, String password, String nome, String cognome, RuoloUtente ruolo) {
        RichiestaRegistrazione nuovoUtente = new RichiestaRegistrazione();
        nuovoUtente.setEmail(email);
        nuovoUtente.setPassword(password);
        nuovoUtente.setNome(nome);
        nuovoUtente.setCognome(cognome);
        nuovoUtente.setRuolo(ruolo);
        return nuovoUtente;
    }

    
} */