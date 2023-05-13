package org.alonso.util;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class ProducerEntityManager {
    @PersistenceContext(name = "ApiRestJPA")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    public EntityManager beanEntityManager() {
        return entityManager;
    }
}
