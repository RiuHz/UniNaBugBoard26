package com.progetto.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.model.Issue;
import com.progetto.service.IssueService;

/* 
L' annotazione RestController è utilizzata per RESTful APIs che sono utulizzate per lavorare con dati come JSON 

@GetMapping un'annotazione composta che funge da scorciatoia per @RequestMapping(method = RequestMethod.GET).

@GetMapping è l'annotazione più recente. 

*/

@RestController
@RequestMapping("/api/issues") // prefisso comune per tutte le richieste
public class IssueControllerNew{

    @Autowired // inietta automaticamente l'istanza di IssueService nel costruttore di issue contrller new
    private IssueService issueService;

    // 1. Recupera tutte le issue
    @GetMapping
    public List<Issue> getIssues() {
        return issueService.recuperaTutteLeIssues();
    }

    // 2. Recupera una sola issue in base all' id passato nell url
    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable Integer id) {
        return issueService.recuperaIssuePerId(id);
    }

    // 3. Salvataggio di una Issue nel database
    @PostMapping("/save")
    public Issue createIssue(@RequestBody Issue issue) {
        return issueService.salvaIssue(issue);
    }

}
