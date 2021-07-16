package com.thirdwheel.carbase.dao.models.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class PredicateCreator {
    public static Predicate yearBetweenStartAndEnd(Path<LocalDate> manufacturingStartDate,
                                                   Path<LocalDate> manufacturingEndDate,
                                                   String year,
                                                   CriteriaBuilder cb) {
        Pattern pattern = Pattern.compile("[0-9]{4}");
        if (!pattern.matcher(year).matches()){
            throw new ConstraintViolationException("Year must match \""+pattern.pattern()+"\"",null);
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

    public static Predicate intIsEqual(Path<Integer> intField, int intValue, CriteriaBuilder cb) {
        return cb.equal(intField, intValue);
    }

    public static Predicate stringIsEqual(Path<String> stringField, String stringValue, CriteriaBuilder cb) {
        return cb.equal(cb.upper(stringField), stringValue.toUpperCase());
    }

    public static Predicate stringStartsWith(Path<String> stringField, String stringBeginning, CriteriaBuilder cb) {
        return cb.like(cb.upper(stringField), stringBeginning.toUpperCase() + "%");
    }
}
