package com.progetto.specification;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.progetto.enums.issue.*;
import com.progetto.model.issues.StorageIssue;

public class StorageIssueSpecification {

    private StorageIssueSpecification() {}

    public static Specification<StorageIssue> filtraStorageIssue(Priorita priorita, Stato stato, Tipo tipo, String userid) {

        return (root, query, criteriaBuilder) -> {

            Predicate filtri = criteriaBuilder.conjunction();

            if (userid != null) {
                criteriaBuilder.equal(root.get("userid"), userid);
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
