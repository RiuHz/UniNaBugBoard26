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

    private Utente utenteNull;
    private Utente utenteConIdVuoto = new Utente();
    private Utente utenteConIdTest = new Utente();

    @BeforeEach
    public void setup() {
        List<Issue> issues = new ArrayList<>();

        utenteConIdVuoto.setId("");
        utenteConIdTest.setId("test-utente");

        List<Utente> utenti = Arrays.asList(
            utenteNull,
            utenteConIdVuoto,
            utenteConIdTest
        );

        for (Priorita priorita: Priorita.values()) {
            for (Stato stato: Stato.values()) {
                for (Tipo tipo: Tipo.values()) {
                    for (Utente utente: utenti) {
                        Issue issue = new Issue();
                        issue.setTitolo("Titolo");
                        issue.setDescrizione("Descrizione");
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
    void filtraIssue_PrioritaBassa_StatoTodo_TipoBug_UtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA, Stato.TODO, Tipo.BUG, utenteConIdTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA)
        .allMatch(issue -> issue.getStato() == Stato.TODO)
        .allMatch(issue -> issue.getTipo() == Tipo.BUG)
        .allMatch(issue -> issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_PrioritaBassa_StatoIn_Progress_TipoDocumentation_UtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA, Stato.IN_PROGRESS, Tipo.DOCUMENTATION, utenteNull);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA)
        .allMatch(issue -> issue.getStato() == Stato.IN_PROGRESS)
        .allMatch(issue -> issue.getTipo() == Tipo.DOCUMENTATION);
    }

    @Test
    void filtraIssue_PrioritaBassa_StatoResolved_TipoQuestion_UtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA, Stato.RESOLVED, Tipo.QUESTION, utenteConIdVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA)
        .allMatch(issue -> issue.getStato() == Stato.RESOLVED)
        .allMatch(issue -> issue.getTipo() == Tipo.QUESTION)
        .allMatch(issue -> issue.getUtente().getId().equals("")); 
    }

    @Test
    void filtraIssue_PrioritaBassa_StatoNull_TipoFeature_UtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.BASSA, null, Tipo.FEATURE, utenteConIdTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.BASSA)
        .allMatch(issue -> issue.getTipo() == Tipo.FEATURE)
        .allMatch(issue -> issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_PrioritaMedia_StatoTodo_TipoDocumentation_UtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, Stato.TODO, Tipo.DOCUMENTATION, utenteConIdVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA)
        .allMatch(issue -> issue.getStato() == Stato.TODO)
        .allMatch(issue -> issue.getTipo() == Tipo.DOCUMENTATION)
        .allMatch(issue -> issue.getUtente().getId().equals(""));
    }

    @Test
    void filtraIssue_PrioritaMedia_StatoIn_Progress_TipoQuestion_UtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, Stato.IN_PROGRESS, Tipo.QUESTION, utenteConIdTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA)
        .allMatch(issue -> issue.getStato() == Stato.IN_PROGRESS)
        .allMatch(issue -> issue.getTipo() == Tipo.QUESTION)
        .allMatch(issue -> issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_PrioritaMedia_StatoResolved_TipoFeature_UtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, Stato.RESOLVED, Tipo.FEATURE, utenteNull);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA)
        .allMatch(issue -> issue.getStato() == Stato.RESOLVED)
        .allMatch(issue -> issue.getTipo() == Tipo.FEATURE);
    }

    @Test
    void filtraIssue_PrioritaMedia_StatoNull_TipoNull_UtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, null, null, utenteConIdVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA)
        .allMatch(issue -> issue.getUtente().getId().equals(""));
    }

    @Test
    void filtraIssue_PrioritaMedia_StatoIn_Progress_TipoBug_UtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.MEDIA, Stato.IN_PROGRESS, Tipo.BUG, utenteConIdTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.MEDIA)
        .allMatch(issue -> issue.getStato() == Stato.IN_PROGRESS)
        .allMatch(issue -> issue.getTipo() == Tipo.BUG)
        .allMatch(issue -> issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_PrioritaAlta_StatoTodo_TipoQuestion_UtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, Stato.TODO, Tipo.QUESTION, utenteNull);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA)
        .allMatch(issue -> issue.getStato() == Stato.TODO)
        .allMatch(issue -> issue.getTipo() == Tipo.QUESTION);
    }

    @Test
    void filtraIssue_PrioritaAlta_StatoIn_Progress_TipoFeature_UtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, Stato.IN_PROGRESS, Tipo.FEATURE, utenteConIdVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA)
        .allMatch(issue -> issue.getStato() == Stato.IN_PROGRESS)
        .allMatch(issue -> issue.getTipo() == Tipo.FEATURE)
        .allMatch(issue -> issue.getUtente().getId().equals(""));
    }

    @Test
    void filtraIssue_PrioritaAlta_StatoResolved_TipoNull_UtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, Stato.RESOLVED, null, utenteConIdTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA)
        .allMatch(issue -> issue.getStato() == Stato.RESOLVED)
        .allMatch(issue -> issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_PrioritaAlta_StatoNull_TipoBug_UtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, null, Tipo.BUG, utenteNull);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA)
        .allMatch(issue -> issue.getTipo() == Tipo.BUG);
    }

    @Test
    void filtraIssue_PrioritaAlta_StatoResolved_TipoDocumentation_UtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(Priorita.ALTA, Stato.RESOLVED, Tipo.DOCUMENTATION, utenteConIdVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getPriorita() == Priorita.ALTA)
        .allMatch(issue -> issue.getStato() == Stato.RESOLVED)
        .allMatch(issue -> issue.getTipo() == Tipo.DOCUMENTATION)
        .allMatch(issue -> issue.getUtente().getId().equals(""));
    }

    @Test
    void filtraIssue_PrioritaNull_StatoTodo_TipoFeature_UtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(null, Stato.TODO, Tipo.FEATURE, utenteConIdTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getStato() == Stato.TODO)
        .allMatch(issue -> issue.getTipo() == Tipo.FEATURE)
        .allMatch(issue -> issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_PrioritaNull_StatoIn_Progress_TipoNull_UtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(null, Stato.IN_PROGRESS, null, utenteNull);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getStato() == Stato.IN_PROGRESS);
    }

    @Test
    void filtraIssue_PrioritaNull_StatoResolved_TipoBug_UtenteVuoto() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(null, Stato.RESOLVED, Tipo.BUG, utenteConIdVuoto);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getStato() == Stato.RESOLVED)
        .allMatch(issue -> issue.getTipo() == Tipo.BUG)
        .allMatch(issue -> issue.getUtente().getId().equals(""));
    }

    @Test
    void filtraIssue_PrioritaNull_StatoNull_TipoDocumentation_UtenteTest() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(null, null, Tipo.DOCUMENTATION, utenteConIdTest);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getTipo() == Tipo.DOCUMENTATION)
        .allMatch(issue ->issue.getUtente().getId().equals("test-utente"));
    }

    @Test
    void filtraIssue_PrioritaNull_StatoNull_TipoQuestion_UtenteNull() {
        Specification<Issue> filtri = IssueSpecification.filtraIssue(null, null, Tipo.QUESTION, utenteNull);

        List<Issue> risultato = issueRepository.findAll(filtri);

        assertThat(risultato)
        .isNotEmpty()
        .allMatch(issue -> issue.getTipo() == Tipo.QUESTION);
    }
}
