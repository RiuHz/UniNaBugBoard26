package com.progetto.api.controller;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.progetto.api.service.IssueService;
import com.progetto.enums.issue.Priorita;
import com.progetto.enums.issue.Stato;
import com.progetto.enums.issue.Tipo;
import com.progetto.models.Issue;
import com.progetto.models.Utente;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;


// Questa annotazione carica SOLO il controller IssueController, rendendo il test veloce (da cambiare sicuramente)
@WebMvcTest(IssueController.class)
public class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private IssueService issueService;

    private Utente utenteTest;

    @BeforeEach
    void setup() {
        utenteTest = new Utente();
    }

    @Test
    public void test01() throws Exception {

        // ARRANGE

        //ACT
        given(issueService.recuperaTutteLeIssues(null, null, null, null)).willReturn(Collections.emptyList());

        //ASSERT
        mockMvc.perform(get("/issues")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void test02() throws Exception {

        // Testare con il recupero di una issue

        //ARRANGE
        Issue issueTest = creaIssueFinta(Priorita.ALTA, Stato.TODO, Tipo.BUG,null);
               
        // ACT
        given(issueService.recuperaTutteLeIssues(Priorita.ALTA, Stato.TODO, Tipo.BUG, null))
                .willReturn(List.of(issueTest));

        // ASSERT
        mockMvc.perform(get("/issues")
                .param("priorita", "ALTA")
                .param("stato", "TODO")
                .param("tipo", "BUG")
                // non metto utente perchè è null
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].priorita").value("ALTA"))
                .andExpect(jsonPath("$[0].stato").value("TODO"))
                .andExpect(jsonPath("$[0].tipo").value("BUG"))
                .andExpect(jsonPath("$[0].utente").value(nullValue()));     
    }

    // Metodo di supporto - Helper
    private Issue creaIssueFinta(Priorita priorita, Stato stato, Tipo tipo,Utente utente) {

        Issue issue = new Issue();
        // Fisse
        issue.setId(1);
        issue.setTitolo("Titolo della Issue");
        issue.setDescrizione("Descrizione della Issue");

        // Variabili
        issue.setPriorita(priorita);
        issue.setStato(stato);
        issue.setTipo(tipo);
        issue.setUtente(utente);


        return issue;
    }
}