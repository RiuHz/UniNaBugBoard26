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

    // Inizio 1° intervallo di test

    @Test
    void filtraIssue_conPrioritaBassaStatoTodoTipoBugUtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA,Stato.TODO,Tipo.BUG,utenteTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA &&
                           issue.getStato() == Stato.TODO && 
                           issue.getTipo() == Tipo.BUG &&
                           issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_conPrioritaBassaStatoIn_ProgressTipoDocumentationUtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA,Stato.IN_PROGRESS,Tipo.DOCUMENTATION,null);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA &&
                           issue.getStato() == Stato.IN_PROGRESS && 
                           issue.getTipo() == Tipo.DOCUMENTATION);
    }

    @Test
    void filtraIssue_conPrioritaBassaStatoResolvedTipoQuestionUtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA,Stato.RESOLVED,Tipo.QUESTION,utenteVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA &&
                           issue.getStato() == Stato.RESOLVED && 
                           issue.getTipo() == Tipo.QUESTION); 
    }

    @Test
    void filtraIssue_conPrioritaBassaStatoNullTipoFeatureUtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA, null,Tipo.FEATURE,utenteTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA &&
                           issue.getTipo() == Tipo.FEATURE  &&
                           issue.getUtente().getId().equals("test-utente"));
    }

    // Inizio 2° intervallo di test

    @Test
    void filtraIssue_conPrioritaMediaStatoTodoTipoDocumentationUtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, Stato.TODO, Tipo.DOCUMENTATION, utenteVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA &&
                           issue.getStato() == Stato.TODO && 
                           issue.getTipo() == Tipo.DOCUMENTATION);
    }

    @Test
    void filtraIssue_conPrioritaMediaStatoIn_ProgressTipoQuestionUtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, Stato.IN_PROGRESS, Tipo.QUESTION, utenteTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA && 
                           issue.getStato() == Stato.IN_PROGRESS && 
                           issue.getTipo() == Tipo.QUESTION && 
                           issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_conPrioritaMediaStatoResolvedTipoFeatureUtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, Stato.RESOLVED, Tipo.FEATURE, null);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA && 
                           issue.getStato() == Stato.RESOLVED && 
                           issue.getTipo() == Tipo.FEATURE);
    }

    @Test
    void filtraIssue_conPrioritaMediaStatoNullTipoNullUtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, null, null,utenteVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA);
    }

    @Test
    void filtraIssue_conPrioritaMediaStatoIn_ProgressTipoBugUtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, Stato.IN_PROGRESS, Tipo.BUG, utenteTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA && 
                           issue.getStato() == Stato.IN_PROGRESS && 
                           issue.getTipo() == Tipo.BUG && 
                           issue.getUtente().getId().equals("test-utente"));
    }

    // Inizio 3° intervallo di test

    @Test
    void filtraIssue_conPrioritaAltaStatoTodoTipoQuestionUtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, Stato.TODO, Tipo.QUESTION, null);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA && 
                           issue.getStato() == Stato.TODO && 
                           issue.getTipo() == Tipo.QUESTION);

    }

    @Test
    void filtraIssue_conPrioritaAltaStatoIn_ProgressTipoFeatureUtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, Stato.IN_PROGRESS, Tipo.FEATURE, utenteVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA && 
                           issue.getStato() == Stato.IN_PROGRESS && 
                           issue.getTipo() == Tipo.FEATURE);
    }

    @Test
    void filtraIssue_conPrioritaAltaStatoResolvedTipoNullUtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, Stato.RESOLVED, null, utenteTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA && 
                           issue.getStato() == Stato.RESOLVED &&
                           issue.getUtente().getId().equals("test-utente"));

    }

    @Test
    void filtraIssue_conPrioritaAltaStatoNullTipoBugUtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, null, Tipo.BUG, null);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA &&
                           issue.getTipo() == Tipo.BUG);

    }

    @Test
    void filtraIssue_conPrioritaAltaStatoResolvedTipoDocumentationUtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, Stato.RESOLVED, Tipo.DOCUMENTATION, utenteVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA && 
                           issue.getStato() == Stato.RESOLVED && 
                           issue.getTipo() == Tipo.DOCUMENTATION);
    }


    // Inizio 4° intervallo di test

    @Test
    void filtraIssue_conPrioritaNullStatoTodoTipoFeatureUtenteTest() {
                Specification<Issue> filtri = IssueSpecification.filtraIssue(null, Stato.TODO, Tipo.FEATURE, utenteTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getStato() == Stato.TODO && 
                           issue.getTipo() == Tipo.FEATURE && 
                           issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_conPrioritaNullStatoIn_ProgressTipoNullUtenteNull() {
                Specification<Issue> filtri = IssueSpecification.filtraIssue(null, Stato.IN_PROGRESS, null, null);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getStato() == Stato.IN_PROGRESS);

    }

    @Test
    void filtraIssue_conPrioritaNullStatoResolvedTipoBugUtenteVuoto() {
                Specification<Issue> filtri = IssueSpecification.filtraIssue(null, Stato.RESOLVED, Tipo.BUG, utenteVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getStato() == Stato.RESOLVED && 
                           issue.getTipo() == Tipo.BUG);
    }

    @Test
    void filtraIssue_conPrioritaNullStatoNullTipoDocumentationUtenteTest() {
                Specification<Issue> filtri = IssueSpecification.filtraIssue(null, null, Tipo.DOCUMENTATION, utenteTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getTipo() == Tipo.DOCUMENTATION && 
                           issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_conPrioritaNullStatoNullTipoQuestionUtenteNull() {
                Specification<Issue> filtri = IssueSpecification.filtraIssue(null, null, Tipo.QUESTION, null);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getTipo() == Tipo.QUESTION);
    }

    // Template

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
