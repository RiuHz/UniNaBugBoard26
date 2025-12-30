package com.progetto.controller;

import com.progetto.model.Issue;
import com.progetto.service.IssueService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssueController{

    @Autowired
    private IssueService issueService;

    @GetMapping
    public List<Issue> getIssues() {
        return issueService.recuperaTutteLeIssues();
    }

    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable Integer id) {
        return issueService.recuperaIssuePerId(id);
    }

    @PostMapping
    public void createIssue(@RequestBody Issue issue) {
        issueService.salvaIssue(issue);
    }

    @PatchMapping("/{id}")
    public void patchIssueByID(@PathVariable Integer id) {
        issueService.impostaIssueComeResolved(id);
    }
}
