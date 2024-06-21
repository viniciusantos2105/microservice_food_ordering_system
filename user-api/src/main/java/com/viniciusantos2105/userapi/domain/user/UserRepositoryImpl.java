package com.viniciusantos2105.userapi.domain.user;

import com.viniciusantos2105.userapi.exception.resource.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void validateUserEmail(String userEmail) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.userEmail = :userEmail", User.class);
        query.setParameter("userEmail", userEmail);

        if (!query.getResultList().isEmpty()) throw new RuntimeException("Email já cadastrado");
    }

    public User findUserByEmail(String userEmail) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.userEmail = :userEmail", User.class);
        query.setParameter("userEmail", userEmail);

        if(query.getResultList().isEmpty()) {
            throw ResourceNotFoundException.create("Usuário não encontrado", "User", 404);
        }
        return query.getSingleResult();
    }
}
