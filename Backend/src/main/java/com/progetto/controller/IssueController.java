package com.progetto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.model.issues.UserInfo;
import com.progetto.enums.issue.Priorita;
import com.progetto.enums.issue.Stato;
import com.progetto.enums.issue.Tipo;
import com.progetto.exception.AuthException;
import com.progetto.exception.StorageException;
import com.progetto.model.issues.StorageIssue;
import com.progetto.model.issues.UserIssue;
import com.progetto.service.IssueService;

@RestController
@RequestMapping("/issues")
public class IssueController{

    @Autowired
    private IssueService issueService;

    @GetMapping
    public List<StorageIssue> getIssues(@RequestParam(required = false, name = "priorita") Priorita priorita, @RequestParam(required = false, name = "stato") Stato stato,
    @RequestParam(required = false, name = "tipo") Tipo tipo, @RequestParam(required = false, name = "userid") UserInfo userinfo) throws AuthException{

        return issueService.recuperaTutteLeIssues(priorita,stato,tipo,userinfo);
    }

    @PostMapping
    public ResponseEntity<String> createIssue(@ModelAttribute UserIssue issue) {
        try {
            issueService.salvaIssue(issue);
        } catch (StorageException error) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok("Issue created");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchIssueByID(@PathVariable Integer id) {
        issueService.impostaIssueComeResolved(id);

        return ResponseEntity.ok("Issue set to Resolved");
    }
}
