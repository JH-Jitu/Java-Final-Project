package dev.repository;

import dev.domain.InterviewListEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class InterviewListRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<InterviewListEntity> getAllInterviews() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM InterviewListEntity", InterviewListEntity.class);
            return query.getResultList();
        }
    }

    public void saveOrUpdateInterview(InterviewListEntity interviewListEntity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(interviewListEntity);
            session.getTransaction().commit();
        }
    }

    public InterviewListEntity getInterviewById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(InterviewListEntity.class, id);
        }
    }

    public void deleteInterview(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            InterviewListEntity interviewListEntity = session.get(InterviewListEntity.class, id);
            if (interviewListEntity != null) {
                session.delete(interviewListEntity);
            }
            session.getTransaction().commit();
        }
    }
}
