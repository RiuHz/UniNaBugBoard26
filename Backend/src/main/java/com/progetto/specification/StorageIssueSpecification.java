package com.progetto.specification;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.progetto.model.issues.UserInfo;
import com.progetto.model.issues.StorageIssue;
import com.progetto.enums.issue.*;

public class StorageIssueSpecification {

    private StorageIssueSpecification() {}

    public static Specification<StorageIssue> filtraStorageIssue(Priorita priorita, Stato stato, Tipo tipo, UserInfo userinfo) {

        return (root, query, criteriaBuilder) -> {

            Predicate filtri = criteriaBuilder.conjunction();

            if (userinfo != null) {
                criteriaBuilder.equal(root.get("userinfo"), userinfo);
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
