package com.progetto.api.specification;

import org.springframework.data.jpa.domain.Specification;

import com.progetto.enums.issue.Priorita;
import com.progetto.enums.issue.Stato;
import com.progetto.enums.issue.Tipo;
import com.progetto.models.Issue;
import com.progetto.models.Utente;

import jakarta.persistence.criteria.Predicate;

public class IssueSpecification {

    public static Specification<Issue> filtraIssue(Priorita priorita, Stato stato, Tipo tipo, Utente utente) {

        return (root, query, criteriaBuilder) -> {

            Predicate filtri = criteriaBuilder.conjunction();

            if (utente != null) {
                filtri = criteriaBuilder.and(filtri, criteriaBuilder.equal(root.get("utente"), utente.getId()));
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
