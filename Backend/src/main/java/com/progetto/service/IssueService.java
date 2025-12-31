package com.progetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.enums.issue.Stato;
import com.progetto.interfaces.ImageStorageSaver;
import com.progetto.model.issues.StorageIssue;
import com.progetto.model.issues.UserIssue;
import com.progetto.repository.IssueRepository;

import jakarta.transaction.Transactional;

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
        	
        	storageIssue.setallegato(url);
        }

        issueRepository.save(storageIssue);
    }

    @Transactional
    public void impostaIssueComeResolved(Integer id) {
        issueRepository.setIssueState(Stato.Resolved, id);
    }

}
