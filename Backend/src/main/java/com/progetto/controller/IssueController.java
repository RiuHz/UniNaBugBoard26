package com.progetto.controller;

import com.progetto.model.issues.*;
import com.progetto.service.IssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.progetto.enums.issue.*;



@RestController
@RequestMapping("/issues")
public class IssueController{

    @Autowired
    private IssueService issueService;

    @GetMapping
    public List<StorageIssue> getIssues(@RequestParam(required = false, name = "priorita") Priorita priorita, @RequestParam(required = false, name = "stato") Stato stato,
    @RequestParam(required = false, name = "tipo") Tipo tipo, @RequestParam(required = false, name = "userid") String userid) {

        return issueService.recuperaTutteLeIssues(priorita,stato,tipo,userid);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageIssue> getIssueById(@PathVariable Integer id) {
        StorageIssue issueRicavata = issueService.recuperaIssuePerId(id);

        if(issueRicavata == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(issueRicavata);
    }

    @PostMapping
    public void createIssue(@RequestBody UserIssue issue) {
        issueService.salvaIssue(issue);
    }

    @PatchMapping("/{id}")
    public void patchIssueByID(@PathVariable Integer id) {
        issueService.impostaIssueComeResolved(id);
    }
}
