package dev.repository;

import dev.domain.RecruiterEntity;
import dev.domain.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("ALL")
@Repository
public class RecruiterRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public RecruiterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List getAllRecruiters() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM RecruiterEntity", RecruiterEntity.class);
            return query.getResultList();
        }
    }

    public void createRecruiter(RecruiterEntity recruiterEntity) {
        try {
            Session session = sessionFactory.getCurrentSession();
//            session.beginTransaction();


            // Create and save UsersEntity
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setEmail(recruiterEntity.getEmail());
            usersEntity.setRole("recruiter"); // Assuming role for recruiter
            session.persist(usersEntity);

            // Save RecruiterEntity
            session.persist(recruiterEntity);


//            session.getTransaction().commit();
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            throw e; // Rethrow the exception to propagate it
        }
    }


    public void updateRecruiter(RecruiterEntity recruiterEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(recruiterEntity);
    }

    public void deleteRecruiter(Long recruiterId) {
        Session session = sessionFactory.getCurrentSession();
        RecruiterEntity recruiter = getRecruiter(recruiterId);
        session.delete(recruiter);
    }

    public RecruiterEntity getRecruiter(Long recruiterId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(RecruiterEntity.class, recruiterId);
    }

    public RecruiterEntity findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<RecruiterEntity> query = session.createQuery("FROM RecruiterEntity WHERE email = :email", RecruiterEntity.class);
        query.setParameter("email", email);
        List<RecruiterEntity> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
