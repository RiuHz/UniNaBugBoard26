package com.progetto.repository;

import com.progetto.enums.StatoIssue;
import com.progetto.model.Issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    @Modifying
    @Query("UPDATE Issue AS issue SET issue.stato = :stato WHERE issue.id = :id")
    void setIssueState(@Param("stato") StatoIssue stato, @Param("id") Integer id);

}
