package dev.repository;

import dev.domain.AdminEntity;
import dev.domain.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import java.util.List;

@SuppressWarnings("rawtypes")
@Repository
public class AdminEntityRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AdminEntityRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List getAllAdminEntities() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM AdminEntity", AdminEntity.class);
            return query.getResultList();
        }
    }

//    public void createAdminEntity(AdminEntity adminEntity) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            session.persist(adminEntity);
//            session.getTransaction().commit();
//        }
//    }

    public void createAdminEntity(AdminEntity adminEntity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Create and save UsersEntity
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setEmail(adminEntity.getEmail());
            usersEntity.setRole(adminEntity.getRole());
            session.persist(usersEntity);

            // Save AdminEntity
            session.persist(adminEntity);


            session.getTransaction().commit();
        }
    }

    public void updateAdminEntity(AdminEntity adminEntity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(adminEntity);
            session.getTransaction().commit();
        }
    }

    public void deleteAdminEntity(Long adminEntityId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AdminEntity adminEntity = session.get(AdminEntity.class, adminEntityId);
            if (adminEntity != null) {
                session.delete(adminEntity);
            }
            session.getTransaction().commit();
        }
    }


    public AdminEntity findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<AdminEntity> query = session.createQuery("FROM AdminEntity WHERE email = :email", AdminEntity.class);
        query.setParameter("email", email);
        List<AdminEntity> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }


}
