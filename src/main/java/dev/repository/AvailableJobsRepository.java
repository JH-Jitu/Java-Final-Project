package dev.repository;

import dev.domain.AvailableJobsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//@SuppressWarnings("ALL")
@Repository
public class AvailableJobsRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AvailableJobsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createAvailableJob(AvailableJobsEntity availableJobsEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(availableJobsEntity);
    }

    public void updateAvailableJob(AvailableJobsEntity availableJobsEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(availableJobsEntity);
    }

    public List<AvailableJobsEntity> getAllAvailableJobs() {
        try (Session session = sessionFactory.openSession()) {
            Query<AvailableJobsEntity> query = session.createQuery("FROM AvailableJobsEntity", AvailableJobsEntity.class);
            return query.getResultList();
        }
    }

    public AvailableJobsEntity getAvailableJobById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AvailableJobsEntity.class, id);
    }

    public void deleteAvailableJob(Long id) {
        Session session = sessionFactory.getCurrentSession();
        AvailableJobsEntity availableJobsEntity = session.get(AvailableJobsEntity.class, id);
        session.delete(availableJobsEntity);
    }
}
