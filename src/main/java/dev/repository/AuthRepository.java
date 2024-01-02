package dev.repository;

import dev.domain.AdminEntity;
import dev.domain.CompanyEntity;
import dev.domain.ProfileEntity;
import dev.domain.RecruiterEntity;
import dev.domain.UsersEntity;
import org.hibernate.Session;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import org.hibernate.SessionFactory;

@SuppressWarnings("rawtypes")
@Repository
@Transactional
public class AuthRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public UsersEntity findUserByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UsersEntity WHERE email = :email");
        query.setParameter("email", email);
        List resultList = query.getResultList();
        return resultList.isEmpty() ? null : (UsersEntity) resultList.get(0);
    }

    public AdminEntity findAdminByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM AdminEntity WHERE email = :email AND password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        List resultList = query.getResultList();
        System.out.println(resultList.get(0));
        return resultList.isEmpty() ? null : (AdminEntity) resultList.get(0);
    }

    public RecruiterEntity findRecruiterByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM RecruiterEntity WHERE email = :email AND password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        List resultList = query.getResultList();
        return resultList.isEmpty() ? null : (RecruiterEntity) resultList.get(0);
    }

    public ProfileEntity findProgrammerByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM ProfileEntity WHERE email = :email AND password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        List resultList = query.getResultList();
        return resultList.isEmpty() ? null : (ProfileEntity) resultList.get(0);
    }

    public CompanyEntity findCompanyByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM CompanyEntity WHERE email = :email AND password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        List resultList = query.getResultList();
        return resultList.isEmpty() ? null : (CompanyEntity) resultList.get(0);
    }
}
