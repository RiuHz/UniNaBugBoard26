package com.progetto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progetto.model.Issue;

/*

 Metodi pronti all'uso (senza scriverli)
 DA VEDERE SE CI SONO ALTRI METODI CHE POSSIAMO USARE

 1) .findAll()      ->   SELECT *
 2) .save(issue)    ->   INSERT o UPDATE
 3) .findById(id)   ->   SELECT con WHERRE id=ecc...
 4) .deleteById(id) ->   DELETE

*/

 // Estendendo JpaRepository, Spring crea automaticamente il codice SQL necessario
 // Indico la classe "Issue" e il tipo della chiave primaria "Integer"

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {}
