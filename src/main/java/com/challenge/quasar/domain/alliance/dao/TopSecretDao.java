package com.challenge.quasar.domain.alliance.dao;

import com.challenge.quasar.domain.alliance.models.TopSecret;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TopSecretDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(TopSecret topSecret) {
        entityManager.merge(topSecret);
    }

    public List<TopSecret> list() {
        return entityManager.createQuery("FROM TopSecret").getResultList();
    }

    public void removeAll() {
        entityManager.createQuery("DELETE FROM TopSecret").executeUpdate();
    }
}
