package com.thirdwheel.carbase.dao.repositories.similaritytagservices;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public abstract class AbstractTagPredicate implements SimilarityTagPredicate {
    protected final SimilarityTag supportedTag;
    protected final EntityManager entityManager;

    public SimilarityTag getSupportedTag() {
        return supportedTag;
    }

    ;
}
