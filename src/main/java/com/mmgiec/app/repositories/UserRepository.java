package com.mmgiec.app.repositories;

import com.mmgiec.app.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user){
        entityManager.persist(user);
    }

    public Optional<User> findByEmail(String email){
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email=:email", User.class);
        query.setParameter("email", email);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }

    public List<User> findAll(){
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }
}
