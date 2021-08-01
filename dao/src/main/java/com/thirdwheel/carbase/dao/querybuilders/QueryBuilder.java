package com.thirdwheel.carbase.dao.querybuilders;

import com.thirdwheel.carbase.dao.models.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Getter
public class QueryBuilder<T extends EntityWithId> {
    private final EntityManager entityManager;

    private final Class<T> tClass;
    private final CriteriaBuilder criteriaBuilder;
    private final CriteriaQuery<T> query;
    private final Root<T> root;
    private final String rootNameFieldName;

    private Subquery<Integer> subquery;
    private Root<T> subqueryRoot;

    private Predicate predicate;

    public QueryBuilder(EntityManager entityManager, Class<T> tClass, String rootNameFieldName) {
        this.entityManager = entityManager;
        this.tClass = tClass;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.query = criteriaBuilder.createQuery(this.tClass);
        this.root = query.from(this.tClass);
        this.rootNameFieldName = rootNameFieldName;
        query.orderBy(criteriaBuilder.asc(root.get(this.rootNameFieldName)));
    }

    public QueryBuilder<T> setSubquery(String rootIdFieldId){
        subquery = query.subquery(Integer.class);
        subqueryRoot = subquery.from(tClass);
        subquery.select(criteriaBuilder.min(subqueryRoot.get(rootIdFieldId)));
        subquery.distinct(true);
        query.where(root.get(rootIdFieldId).in(subquery));
        return this;
    }

    public QueryBuilder<T> setSubqueryGroupBy(List<Expression<?>> groupElements){
        assert subquery!=null;
        subquery.groupBy(groupElements);
        return this;
    }

    public TypedQuery<T> build(){
        if (subquery==null){
            query.where(predicate);
        } else {
            subquery.where(predicate);
        }
        return entityManager.createQuery(query);
    }

    public QueryBuilder<T> setModificationFetch(){
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.generation).fetch(Generation.Fields.model)
                .fetch(Model.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.rearSuspension);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.frontSuspension);
        root.fetch(Modification.Fields.engineModification).fetch(EngineModification.Fields.engine)
                .fetch(Engine.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        return this;
    }

    public QueryBuilder<T> setPredicate(Predicate predicate){
        this.predicate = predicate;
        return this;
    }
}
