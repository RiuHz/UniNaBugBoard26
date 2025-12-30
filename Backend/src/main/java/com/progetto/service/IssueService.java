package com.progetto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.progetto.model.Issue;
import com.progetto.repository.IssueRepository;

// Il tag Service indica che questa classe fornisce servizi di logica di business
@Service 
public class IssueService {

    private IssueRepository issueRepository;
    
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    // 1. Recupera tutti i dati
    public List<Issue> recuperaTutteLeIssues() {
        return issueRepository.findAll();
    }

    // 2. Recupera un solo dato in base all id che gli passiamo
    public Issue recuperaIssuePerId(Integer id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue non trovata con ID: " + id));
    }

    // 3. Salava una issue nel database
    public Issue salvaIssue(Issue issue){
        return issueRepository.save(issue);
    }

}