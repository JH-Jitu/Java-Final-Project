package dev.repository;

import dev.domain.ProfileEntity;
import dev.domain.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("CallToPrintStackTrace")
@Repository
public class ProfileRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProfileRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<ProfileEntity> getAllProfiles() {
        Session session = sessionFactory.getCurrentSession();
        Query<ProfileEntity> query = session.createQuery("FROM ProfileEntity", ProfileEntity.class);
        return query.list();
    }

    public void createProfile(ProfileEntity profileEntity) {
        try {
            Session session = sessionFactory.getCurrentSession();

            // Create and save UsersEntity
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setEmail(profileEntity.getEmail());
            usersEntity.setRole("programmer"); // Assuming role for programmer
            session.persist(usersEntity);

            // Save ProfileEntity
            session.persist(profileEntity);

        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            throw e; // Rethrow the exception to propagate it
        }
    }

    public void updateProfile(ProfileEntity profileEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(profileEntity);
    }

    public void deleteProfile(Long profileId) {
        Session session = sessionFactory.getCurrentSession();
        ProfileEntity profileEntity = session.get(ProfileEntity.class, profileId);
        if (profileEntity != null) {
            session.delete(profileEntity);
        }
    }

    public ProfileEntity getProfile(Long profileId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProfileEntity.class, profileId);
    }

    public ProfileEntity findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<ProfileEntity> query = session.createQuery("FROM ProfileEntity WHERE email = :email", ProfileEntity.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }
}
