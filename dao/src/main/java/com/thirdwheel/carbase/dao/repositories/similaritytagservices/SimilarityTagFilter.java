package com.thirdwheel.carbase.dao.repositories.similaritytagservices;

import com.thirdwheel.carbase.dao.models.Modification;

import javax.persistence.criteria.Root;

public interface SimilarityTagFilter {
    SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root);

    SimilarityTag getSupportedTag();
}
