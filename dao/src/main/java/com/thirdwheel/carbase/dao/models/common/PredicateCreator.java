package com.thirdwheel.carbase.dao.models.common;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Service
public class PredicateCreator {
    @PersistenceContext
    protected EntityManager entityManager;

    public Predicate yearBetweenStartAndEnd(Path<LocalDate> manufacturingStartDate,
                                            Path<LocalDate> manufacturingEndDate,
                                            Integer year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        LocalDate beginningOfYear = LocalDate.parse(year.toString() + "-01-01");
        LocalDate endOfYear = LocalDate.parse(year.toString() + "-12-31");
        Predicate startPredicate = cb.or(
                cb.lessThanOrEqualTo(manufacturingStartDate, endOfYear),
                cb.isNull(manufacturingStartDate));
        Predicate endPredicate = cb.or(
                cb.greaterThanOrEqualTo(manufacturingEndDate, beginningOfYear),
                cb.isNull(manufacturingEndDate));
        return cb.and(startPredicate, endPredicate);
    }

    public Predicate stringStartsWithOrHasSubstring(Path<String> stringField, String stringBeginningOrSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        if (stringBeginningOrSubstring.length() > 1) {
            return cb.like(cb.upper(stringField), "%" + stringBeginningOrSubstring.toUpperCase() + "%");
        } else {
            return cb.like(cb.upper(stringField), stringBeginningOrSubstring.toUpperCase() + "%");
        }
    }
}
