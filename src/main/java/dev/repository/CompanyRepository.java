package dev.repository;

import dev.domain.AdminEntity;
import dev.domain.CompanyEntity;
import dev.domain.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import org.hibernate.query.Query;

@Repository
public class CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    public List<CompanyEntity> getAllCompanies() {
        return entityManager.createQuery("FROM CompanyEntity", CompanyEntity.class).getResultList();
    }

    public void createCompany(CompanyEntity companyEntity) {
        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();

        // Create and save UsersEntity
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail(companyEntity.getEmail());
        usersEntity.setRole("company"); // Assuming role for the company
        session.persist(usersEntity);

        // Save CompanyEntity
        session.persist(companyEntity);

//        session.getTransaction().commit();
    }

    public void updateCompany(CompanyEntity companyEntity) {
        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
        session.merge(companyEntity);
//        session.getTransaction().commit();
    }

    public void deleteCompany(Long companyId) {
        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
        CompanyEntity companyEntity = session.get(CompanyEntity.class, companyId);
        session.delete(companyEntity);
//        session.getTransaction().commit();
    }

    public CompanyEntity getCompany(Long companyId) {
        return sessionFactory.getCurrentSession().get(CompanyEntity.class, companyId);
    }

//    public CompanyEntity findByEmail(String email) {
//        Query<CompanyEntity> query = (Query<CompanyEntity>) sessionFactory.getCurrentSession()
//                .createQuery("FROM CompanyEntity WHERE email = :email")
//                .setParameter("email", email);
//        return query.uniqueResult();
//    }
    public CompanyEntity findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<CompanyEntity> query = session.createQuery("FROM CompanyEntity WHERE email = :email", CompanyEntity.class);
        query.setParameter("email", email);
        List<CompanyEntity> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
