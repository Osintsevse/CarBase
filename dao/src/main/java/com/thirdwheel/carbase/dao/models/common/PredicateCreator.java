package com.thirdwheel.carbase.dao.models.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;

public class PredicateCreator {
    public static Predicate yearBetweenStartAndEnd(Path<LocalDate> start, Path<LocalDate> end, String year,
                                                   CriteriaBuilder cb) {
        LocalDate beginningOfYear = LocalDate.parse(year + "-01-01");
        LocalDate endOfYear = LocalDate.parse(year + "-12-31");
        Predicate startPredicate = cb.or(
                cb.lessThanOrEqualTo(start, endOfYear),
                cb.isNull(start));
        Predicate endPredicate = cb.or(
                cb.greaterThanOrEqualTo(end, beginningOfYear),
                cb.isNull(end));
        return cb.and(startPredicate, endPredicate);
    }

    public static Predicate intIsEqual(Path<Integer> intField, int intValue, CriteriaBuilder cb) {
        return cb.equal(intField, intValue);
    }

    public static Predicate stringIsEqual(Path<String> stringField, String stringValue, CriteriaBuilder cb) {
        return cb.equal(cb.upper(stringField), stringValue.toUpperCase());
    }

    public static Predicate stringIsLike(Path<String> stringField, String stringValue, CriteriaBuilder cb) {
        return cb.like(cb.upper(stringField), stringValue.toUpperCase() + "%");
    }
}
