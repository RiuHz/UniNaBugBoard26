package com.progetto.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import com.progetto.exception.StorageException;
import com.progetto.model.issues.UserInfo;
import com.progetto.auth.AmazonWebServiceCognito;
import com.progetto.exception.AuthException;
import java.util.Map;
import java.util.Set;

import jakarta.transaction.Transactional;

@Service 
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ImageStorageSaver amazonWebServiceS3;
    
    @Autowired
    private AmazonWebServiceCognito amazonWebServiceCognito;

   public List<StorageIssue> recuperaTutteLeIssues(Priorita priorita, Stato stato, Tipo tipo, UserInfo userinfo) throws AuthException {
    // Recupera le issues dal DB
    if (userinfo != null && userinfo.getUserid() != null) {
        userinfo = amazonWebServiceCognito.recuperaInfomazioniUtentePublic(userinfo.getUserid());
    }
    Specification<StorageIssue> filtri = StorageIssueSpecification.filtraStorageIssue(priorita, stato, tipo, userinfo);
    List<StorageIssue> issues = issueRepository.findAll(filtri);

    //Trova tutti gli ID utente unici presenti in queste issues
    Set<String> uniqueUserIds = issues.stream()
        .map(StorageIssue::getUserid)
        .filter(id -> id != null && !id.isEmpty())
        .collect(Collectors.toSet());

    // Crea una mappa ID -> UserInfo per fare cache per ottimizzare
    Map<String, UserInfo> userCache = new HashMap<>();

    for (String userId : uniqueUserIds) {
        try {
            // Chiamiamo Cognito UNA volta per ogni utente distinto, non per ogni issue
            UserInfo info = amazonWebServiceCognito.recuperaInfomazioniUtentePublic(userId);
            userCache.put(userId, info);
        } catch (AuthException e) {
            // Se fallisce un utente, logghiamo ma non blocchiamo tutto l'elenco
            System.err.println("Impossibile recuperare info per user: " + userId);
        }
    }

    // 4. Popola le issues usando la cache
    for (StorageIssue si : issues) {
        if (si.getUserid() != null && userCache.containsKey(si.getUserid())) {
            si.setUserInfo(userCache.get(si.getUserid()));
        }
    }

        return issues;
    }

    public void salvaIssue(UserIssue userIssue) throws StorageException{
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
