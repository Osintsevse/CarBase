package com.thirdwheel.carbase.dao.repositories.similaritytagservices;

import com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices.Acceleration0100TagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices.MaxPowerTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices.WDTypeTagFilter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SimilarityTagFilterFactory {
    @PersistenceContext
    private EntityManager entityManager;

    public SimilarityTagFilter getByTag(SimilarityTag tag) {
        switch (tag) {
            case WD_TYPE:
                return new WDTypeTagFilter(entityManager);
            case ACCELERATION0100:
                return new Acceleration0100TagFilter(entityManager);
            case MAX_POWER:
                return new MaxPowerTagFilter(entityManager);
        }
        return null;
    }
}
