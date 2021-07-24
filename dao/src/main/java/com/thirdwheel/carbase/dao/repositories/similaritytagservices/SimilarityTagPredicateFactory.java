package com.thirdwheel.carbase.dao.repositories.similaritytagservices;

import com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices.Acceleration0100TagPredicate;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices.MaxPowerTagPredicate;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices.WDTypeTagPredicate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SimilarityTagPredicateFactory {
    @PersistenceContext
    private EntityManager entityManager;

    public SimilarityTagPredicate getByTag(SimilarityTag tag) {
        switch (tag) {
            case WD_TYPE:
                return new WDTypeTagPredicate(entityManager);
            case ACCELERATION0100:
                return new Acceleration0100TagPredicate(entityManager);
            case MAX_POWER:
                return new MaxPowerTagPredicate(entityManager);
        }
        return null;
    }
}
