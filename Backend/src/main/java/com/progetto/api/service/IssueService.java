package com.progetto.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.progetto.api.repository.IssueRepository;
import com.progetto.api.specification.IssueSpecification;
import com.progetto.aws.auth.AmazonWebServiceCognito;
import com.progetto.enums.issue.Priorita;
import com.progetto.enums.issue.Stato;
import com.progetto.enums.issue.Tipo;
import com.progetto.exception.AuthException;
import com.progetto.exception.StorageException;
import com.progetto.interfaces.ImageStorageSaver;
import com.progetto.models.Issue;
import com.progetto.models.Utente;

import jakarta.transaction.Transactional;

@Service 
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ImageStorageSaver amazonWebServiceS3;

    @Autowired
    private AmazonWebServiceCognito amazonWebServiceCognito;

    public List<Issue> recuperaTutteLeIssues(Priorita priorita, Stato stato, Tipo tipo, Utente utente) throws AuthException {

        Specification<Issue> filtri = IssueSpecification.filtraIssue(priorita, stato, tipo, utente);

        List<Issue> issues = issueRepository.findAll(filtri);

        for (Issue issue : issues) 
            if (issue.getUtente() != null && issue.getUtente().getId() != null)
                issue.setUtente(amazonWebServiceCognito.recuperaInfomazioniUtente(issue.getUtente().getId()));

        return issues;
    }

    public void salvaIssue(Issue issue) throws StorageException {
        if (issue.getAllegato().getFile() != null) {
            String url = amazonWebServiceS3.saveImage(issue.getAllegato().getFile());
            
            issue.getAllegato().setUrl(url);
        }

        issueRepository.save(issue);
    }

    @Transactional
    public void impostaIssueComeResolved(Integer id) {
        issueRepository.setIssueState(Stato.RESOLVED, id);
    }

}
