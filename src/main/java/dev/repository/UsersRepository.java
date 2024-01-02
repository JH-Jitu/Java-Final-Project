package dev.repository;

import dev.domain.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("rawtypes")
@Repository
@Transactional
public class UsersRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(UsersEntity usersEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(usersEntity);
    }

    public UsersEntity findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UsersEntity WHERE email = :email");
        query.setParameter("email", email);
        List resultList = query.getResultList();
        return resultList.isEmpty() ? null : (UsersEntity) resultList.get(0);
    }
}
