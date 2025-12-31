package com.progetto.service;

import com.progetto.enums.StatoIssue;
import com.progetto.interfaces.ImageStorageSaver;
import com.progetto.model.issues.*;
import com.progetto.repository.IssueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service 
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ImageStorageSaver amazonWebServiceS3;
    
    public List<StorageIssue> recuperaTutteLeIssues() {
        return issueRepository.findAll();
    }

    public StorageIssue recuperaIssuePerId(Integer id) {
        return issueRepository.findById(id).orElse(null);
    }

    public void salvaIssue(UserIssue userIssue){
    	StorageIssue storageIssue = StorageIssue.fromUserIssue(userIssue);
    	
    	
        if (userIssue.getImmagine() != null) {
        	String url = amazonWebServiceS3.saveImage(userIssue.getImmagine());
        	
        	storageIssue.setImmagine(url);
        }

        issueRepository.save(storageIssue);
    }

    @Transactional
    public void impostaIssueComeResolved(Integer id) {
        issueRepository.setIssueState(StatoIssue.Resolved, id);
    }

}
