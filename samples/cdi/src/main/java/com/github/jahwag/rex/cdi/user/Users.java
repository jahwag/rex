package com.github.jahwag.rex.cdi.user;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class Users {

    @PersistenceContext
    EntityManager em;

    public User findBy(String name) {
        return em.createNamedQuery(User.FIND_BY_NAME, User.class)
                 .setParameter("name", name)
                 .getSingleResult();
    }

    public void save(User user) {
        em.merge(user);
    }

}
