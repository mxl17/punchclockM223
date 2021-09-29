package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;
import io.quarkus.security.Authenticated;

@Authenticated
@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    public EntryService() {
    }

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public void deleteEntry(Long id) {
        entityManager.remove(entityManager.find(Entry.class, id));
    }

    @Transactional
    public Entry findEntryByID(Long id) {
        Entry findEntry = entityManager.find(Entry.class, id);
        return findEntry;
    }

    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }

    @Transactional
    public Entry update(Entry entry) {
        entityManager.merge(entry);
        return entry;
    }
}
