package com.thirdwheel.carbase.dao.repositories.similaritytagservices;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
public abstract class AbstractTagFilter implements SimilarityTagFilter {
    protected final SimilarityTag supportedTag;
    @PersistenceContext
    protected EntityManager entityManager;

    public SimilarityTag getSupportedTag() {
        return supportedTag;
    }
}
