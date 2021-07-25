package com.thirdwheel.carbase.dao.repositories.similaritytagservices;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

@Getter
@RequiredArgsConstructor
public class SimilarityPredicateAndGroupElement {
    public final Predicate predicate;
    public final Expression<?> groupElement;
}
