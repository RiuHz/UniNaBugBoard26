package com.progetto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.progetto.enums.issue.Stato;
import com.progetto.model.issues.StorageIssue;

@Repository
public interface IssueRepository extends JpaRepository<StorageIssue, Integer> {

    @Modifying
    @Query("UPDATE Issue AS issue SET issue.stato = :stato WHERE issue.id = :id")
    void setIssueState(@Param("stato") Stato stato, @Param("id") Integer id);

}
