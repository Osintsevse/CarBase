package com.thirdwheel.carbase.dao.querybuilders;

import com.thirdwheel.carbase.dao.models.EntityWithId;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;

@Getter
public abstract class AbstractQueries<T extends EntityWithId> {
    protected final Root<T> root;
    private final EntityManager entityManager;
    private final Class<T> tClass;
    private final CriteriaBuilder criteriaBuilder;
    private final CriteriaQuery<T> query;
    private final String rootNameFieldName;

    private Subquery<Integer> subquery;
    private Root<T> subqueryRoot;

    private Predicate predicate;

    public AbstractQueries(EntityManager entityManager, Class<T> tClass, String rootNameFieldName) {
        this.entityManager = entityManager;
        this.tClass = tClass;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.query = criteriaBuilder.createQuery(this.tClass);
        this.root = query.from(this.tClass);
        this.rootNameFieldName = rootNameFieldName;
        query.orderBy(criteriaBuilder.asc(root.get(this.rootNameFieldName)));
    }

    protected AbstractQueries<T> setSubqueryByMinId(String rootIdFieldName) {
        subquery = query.subquery(Integer.class);
        subqueryRoot = subquery.from(tClass);
        subquery.select(criteriaBuilder.min(subqueryRoot.get(rootIdFieldName)));
        subquery.distinct(true);
        query.where(root.get(rootIdFieldName).in(subquery));
        return this;
    }

    public abstract AbstractQueries<T> setSubqueryByMinId();

    public AbstractQueries<T> setGroupByInSubquery(List<Expression<?>> groupElements) {
        if (subquery == null) throw new RuntimeException("Subquery was not created.");
        subquery.groupBy(groupElements);
        return this;
    }

    public AbstractQueries<T> setGroupByInSubquery(Path<Object> path) {
        if (subquery == null) throw new RuntimeException("Subquery was not created.");
        subquery.groupBy(path);
        return this;
    }

    public AbstractQueries<T> setGroupByNameInSubquery() {
        setGroupByInSubquery(subqueryRoot.get(rootNameFieldName));
        return this;
    }

    public TypedQuery<T> build() {
        if (subquery == null) {
            query.where(predicate);
        } else {
            subquery.where(predicate);
        }
        return entityManager.createQuery(query);
    }

    public abstract AbstractQueries<T> setFetch();

    public abstract AbstractQueries<T> byVendorId(Integer vendorId);

    public AbstractQueries<T> setPredicate(Predicate predicate) {
        addPredicateAnd(predicate);
        return this;
    }

    public AbstractQueries<T> setPredicateByAnd(Predicate... predicate) {
        addPredicateAnd(criteriaBuilder.and(predicate));
        return this;
    }

    public AbstractQueries<T> addPredicateAnd(Predicate predicate) {
        if (this.predicate != null) {
            this.predicate = criteriaBuilder.and(this.predicate, predicate);
        } else {
            this.predicate = predicate;
        }
        return this;
    }

    public Root<T> getLowestRoot() {
        if (getSubquery() == null) {
            return getRoot();
        } else {
            return getSubqueryRoot();
        }
    }

    public AbstractQueries<T> setNameIsEqual(String name) {
        addPredicateAnd(getCriteriaBuilder().equal(getLowestRoot().get(rootNameFieldName), name));
        return this;
    }

    protected AbstractQueries<T> setYearIsBetween(Path<LocalDate> manufacturingStartDate,
                                                  Path<LocalDate> manufacturingEndDate,
                                                  Integer year) {
        LocalDate beginningOfYear = LocalDate.parse(year + "-01-01");
        LocalDate endOfYear = LocalDate.parse(year + "-12-31");

        Predicate startPredicate = criteriaBuilder.or(
                criteriaBuilder.lessThanOrEqualTo(manufacturingStartDate, endOfYear),
                criteriaBuilder.isNull(manufacturingStartDate));
        Predicate endPredicate = criteriaBuilder.or(
                criteriaBuilder.greaterThanOrEqualTo(manufacturingEndDate, beginningOfYear),
                criteriaBuilder.isNull(manufacturingEndDate));

        addPredicateAnd(criteriaBuilder.and(startPredicate, endPredicate));
        return this;
    }

    public abstract AbstractQueries<T> setYearIsBetween(Integer year);

    public AbstractQueries<T> setNameStartsWithOrHasSubstring(String stringBeginningOrSubstring) {
        if (stringBeginningOrSubstring.length() > 1) {
            addPredicateAnd(criteriaBuilder.like(criteriaBuilder.upper(getLowestRoot().get(rootNameFieldName)),
                    "%" + stringBeginningOrSubstring.toUpperCase() + "%"));
        } else {
            addPredicateAnd(criteriaBuilder.like(criteriaBuilder.upper(getLowestRoot().get(rootNameFieldName)),
                    stringBeginningOrSubstring.toUpperCase() + "%"));
        }
        return this;
    }
}
