package com.progetto.specification;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.progetto.enums.issue.*;
import com.progetto.models.Issue;
import com.progetto.models.Utente;

public class StorageIssueSpecification {

    private StorageIssueSpecification() {}

    public static Specification<Issue> filtraIssue(Priorita priorita, Stato stato, Tipo tipo, Utente utente) {

        return (root, query, criteriaBuilder) -> {

            Predicate filtri = criteriaBuilder.conjunction();

            if (utente != null) {
                criteriaBuilder.equal(root.get("utente"), utente);
            }

            if (priorita != null) {
                filtri = criteriaBuilder.and(filtri, criteriaBuilder.equal(root.get("priorita"), priorita));
            }

            if (stato != null) {
                filtri = criteriaBuilder.and(filtri, criteriaBuilder.equal(root.get("stato"), stato));
            }

            if (tipo != null) {
                filtri = criteriaBuilder.and(filtri, criteriaBuilder.equal(root.get("tipo"), tipo));
            }

            return filtri;
        };
    }
}
