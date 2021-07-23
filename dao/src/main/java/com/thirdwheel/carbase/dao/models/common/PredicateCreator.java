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
                                                   String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Pattern pattern = Pattern.compile("[0-9]{4}");
        if (!pattern.matcher(year).matches()) {
            throw new ConstraintViolationException("Year must match \"" + pattern.pattern() + "\"", null);
        }
        LocalDate beginningOfYear = LocalDate.parse(year + "-01-01");
        LocalDate endOfYear = LocalDate.parse(year + "-12-31");
        Predicate startPredicate = cb.or(
                cb.lessThanOrEqualTo(manufacturingStartDate, endOfYear),
                cb.isNull(manufacturingStartDate));
        Predicate endPredicate = cb.or(
                cb.greaterThanOrEqualTo(manufacturingEndDate, beginningOfYear),
                cb.isNull(manufacturingEndDate));
        return cb.and(startPredicate, endPredicate);
    }

    public Predicate intIsEqual(Path<Integer> intField, int intValue) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        return cb.equal(intField, intValue);
    }

    public Predicate stringIsEqual(Path<String> stringField, String stringValue) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        return cb.equal(cb.upper(stringField), stringValue.toUpperCase());
    }

    public Predicate stringStartsWith(Path<String> stringField, String stringBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        return cb.like(cb.upper(stringField), stringBeginning.toUpperCase() + "%");
    }
}
