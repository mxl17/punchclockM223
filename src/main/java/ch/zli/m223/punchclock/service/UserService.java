package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.Workspace;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class UserService {

    @Inject
    private EntityManager entityManager;

    public UserService() {

    }

    @Transactional
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Transactional
    public User findUserByID(Long id) {
        User findUser = entityManager.find(User.class, id);
        return findUser;
    }

    @SuppressWarnings("unchecked")
    public List<Workspace> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    @Transactional
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }
}
