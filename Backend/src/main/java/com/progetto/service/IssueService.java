package com.progetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.enums.Stato;
import com.progetto.interfaces.ImageStorage;
import com.progetto.model.Issue;
import com.progetto.repository.IssueRepository;

import jakarta.transaction.Transactional;

@Service 
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ImageStorage amazonWebServiceS3Repository;

    public List<Issue> recuperaTutteLeIssues() {
        return issueRepository.findAll();
    }

    public Issue recuperaIssuePerId(Integer id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue non trovata con ID: " + id));
    }

    public void salvaIssue(Issue issue){

        if (issue.getAllegato() != null) {
            //String url = amazonWebServiceS3Repository.saveImage(issue.getAllegato());
            //issue.setAllegato(url);
        }

        issueRepository.save(issue);
    }

    @Transactional
    public void impostaIssueComeResolved(Integer id) {
        issueRepository.setIssueState(Stato.Resolved, id);
    }

}
