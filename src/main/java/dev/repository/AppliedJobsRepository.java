package dev.repository;

import dev.domain.AppliedJobsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

//@SuppressWarnings("CallToPrintStackTrace")
@Repository
public class AppliedJobsRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AppliedJobsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createAppliedJob(AppliedJobsEntity appliedJobsEntity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.persist(appliedJobsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateAppliedJob(AppliedJobsEntity appliedJobsEntity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.merge(appliedJobsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<AppliedJobsEntity> getAllAppliedJobs() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM AppliedJobsEntity", AppliedJobsEntity.class);
            return query.getResultList();
        }
    }

    public void deleteAppliedJob(Long appliedJobId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            AppliedJobsEntity appliedJobsEntity = session.get(AppliedJobsEntity.class, appliedJobId);
            session.delete(appliedJobsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public AppliedJobsEntity findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM AppliedJobsEntity WHERE programmer.email = :email", AppliedJobsEntity.class);
            query.setParameter("email", email);
            return (AppliedJobsEntity) ((org.hibernate.query.Query<?>) query).uniqueResult();
        }
    }
}
