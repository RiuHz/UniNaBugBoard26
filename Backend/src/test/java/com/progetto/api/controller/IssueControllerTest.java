package com.progetto.api.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.progetto.api.service.IssueService;
import com.progetto.enums.issue.Priorita;
import com.progetto.enums.issue.Stato;
import com.progetto.enums.issue.Tipo;
import com.progetto.models.Utente;

// Questa annotazione carica SOLO il controller IssueController, rendendo il test veloce (da cambiare sicuramente)
@WebMvcTest(IssueController.class)
public class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private IssueService issueService;

    @Test
    public void test01() throws Exception {

        // ARRANGE
        Priorita priorita = null;
        Stato stato = null;
        Tipo tipo = null;
        Utente utente = null;

        //ACT
        given(issueService.recuperaTutteLeIssues(priorita, stato, tipo, utente)).willReturn(Collections.emptyList());

        //ASSERT
        mockMvc.perform(get("/issues")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); 
    }

    @Test
    public void test02() throws Exception {

        // Testare con il recupero di una issue

        // ARRANGE

        //ACT

        //ASSERT
      
    }
}