package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private final EntityManager entityManager;


    @Override
    public List<User> findByFilter(UserFilter userFilter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> user = criteria.from(User.class);
        criteria.select(user);

        List<Predicate> predicates = new ArrayList<>();
        if ( userFilter.firstname() != null){
            predicates.add(cb.like(user.get("firstname"), userFilter.firstname()));
        }
        if ( userFilter.birthDate() != null){
            predicates.add(cb.lessThan(user.get("birthDate"), userFilter.birthDate()));
        }
        if ( userFilter.lastname() != null){
            predicates.add(cb.like(user.get("lastname"), userFilter.lastname()));
        }
        criteria.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }
}
