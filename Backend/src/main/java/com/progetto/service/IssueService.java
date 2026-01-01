package com.progetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.progetto.specification.StorageIssueSpecification;
import com.progetto.interfaces.ImageStorageSaver;
import com.progetto.repository.IssueRepository;
import com.progetto.model.issues.StorageIssue;
import com.progetto.model.issues.UserIssue;
import com.progetto.enums.issue.Priorita;
import com.progetto.enums.issue.Stato;
import com.progetto.enums.issue.Tipo;

import jakarta.transaction.Transactional;

@Service 
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ImageStorageSaver amazonWebServiceS3;
    
    public List<StorageIssue> recuperaTutteLeIssues(Priorita priorita, Stato stato, Tipo tipo, String userid) {
        Specification<StorageIssue> filtri = StorageIssueSpecification.filtraStorageIssue(priorita, stato, tipo, userid);
        return issueRepository.findAll(filtri);
    }

    public void salvaIssue(UserIssue userIssue){
    	StorageIssue storageIssue = StorageIssue.fromUserIssue(userIssue);

        if (userIssue.getAllegato() != null) {
        	String url = amazonWebServiceS3.saveImage(userIssue.getAllegato());
        	
        	storageIssue.setAllegato(url);
        }

        issueRepository.save(storageIssue);
    }

    @Transactional
    public void impostaIssueComeResolved(Integer id) {
        issueRepository.setIssueState(Stato.RESOLVED, id);
    }

}
