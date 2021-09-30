package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Workspace;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class WorkspaceService {
    @Inject
    private EntityManager entityManager;

    public WorkspaceService() {

    }

    @Transactional
    public Workspace createWorkspace(Workspace workspace) {
        entityManager.persist(workspace);
        return workspace;
    }

    @Transactional
    public void deleteWorkspace(Long id) {
        entityManager.remove(entityManager.find(Workspace.class, id));
    }

    @Transactional
    public Workspace findWorkspaceByID(Long id) {
        Workspace findWorkspace = entityManager.find(Workspace.class, id);
        return findWorkspace;
    }

    @SuppressWarnings("unchecked")
    public List<Workspace> findAll() {
        var query = entityManager.createQuery("FROM Workspace");
        return query.getResultList();
    }

    @Transactional
    public Workspace update(Workspace workspace) {
        entityManager.merge(workspace);
        return workspace;
    }
}
