package com.progetto.repository;

import com.progetto.model.Issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*

 Metodi pronti all'uso (senza scriverli)
 DA VEDERE SE CI SONO ALTRI METODI CHE POSSIAMO USARE

 1) .findAll()      ->   SELECT *
 2) .save(issue)    ->   INSERT o UPDATE
 3) .findById(id)   ->   SELECT con WHERRE id=ecc...
 4) .deleteById(id) ->   DELETE

*/

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    @Modifying
    @Query("UPDATE Issue SET Issue.status = :status WHERE Issue.id = :id")
    void setIssueState(@Param("status") Stato status, @Param("id") Integer id);

}
