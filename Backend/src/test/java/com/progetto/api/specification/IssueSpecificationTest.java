package com.progetto.api.specification;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import com.progetto.api.repository.IssueRepository;
import com.progetto.enums.issue.Priorita;
import com.progetto.enums.issue.Stato;
import com.progetto.enums.issue.Tipo;
import com.progetto.models.Issue;
import com.progetto.models.Utente;

@DataJpaTest
public class IssueSpecificationTest {
 
    @Autowired
    private IssueRepository issueRepository;

    @BeforeEach
    public void setup() {
        List<Issue> issues = new ArrayList<>();

        Utente utenteNull = new Utente();
        Utente utenteVuoto = new Utente();
        Utente utenteTest = new Utente();

        utenteVuoto.setId("");
        utenteTest.setId("test-utente");

        List<Utente> utenti = Arrays.asList(
            utenteNull,
            utenteVuoto,
            utenteTest
        );

        for (Priorita priorita: Priorita.values()) {
            for (Stato stato: Stato.values()) {
                for (Tipo tipo: Tipo.values()) {
                    for (Utente utente: utenti) {
                        Issue issue = new Issue();
                        issue.setPriorita(priorita);
                        issue.setStato(stato);
                        issue.setTipo(tipo);
                        issue.setUtente(utente);
                        issues.add(issue);
                    }
                }
            }
        }

        issueRepository.saveAll(issues);
    }

    @Test
    void filtraIssue_conPrioritaBassa() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA, null, null, null);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA);
        // Aggiungere ulteriori AllMatch per verificare che gli altri campi possano essere di qualsiasi valore
    }
}
