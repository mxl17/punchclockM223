package ch.zli.m223.punchclock.service;


import ch.zli.m223.punchclock.domain.Category;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class CategoryService {
    @Inject
    private EntityManager entityManager;

    public CategoryService() {
    }

    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Transactional
    public void deleteCategory(Long id) {
        entityManager.remove(entityManager.find(Category.class, id));
    }

    @Transactional
    public Category findCategoryByID(Long id) {
        Category findCategory = entityManager.find(Category.class, id);
        return findCategory;
    }

    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category");
        return query.getResultList();
    }

    @Transactional
    public Category update(Category category) {
        entityManager.merge(category);
        return category;
    }
}
