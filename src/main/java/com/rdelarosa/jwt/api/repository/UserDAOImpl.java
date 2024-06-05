package com.rdelarosa.jwt.api.repository;

import com.rdelarosa.jwt.api.entity.Phone;
import com.rdelarosa.jwt.api.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import com.rdelarosa.jwt.api.entity.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public UserDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    // implement save method
    @Override
    @Transactional
    public User save(User theUser) {
        entityManager.persist(theUser);
        return theUser;
    }

    @Override
    @Transactional
    public List<User> saveAll(List<User> listUser){

        for (User user:listUser) {
            entityManager.persist(user);
        }
        return listUser;
    }

    @Override
    @Transactional
    public Phone savePhone(Phone thePhone) {
        entityManager.persist(thePhone);
        return thePhone;
    }

    @Override
    @Transactional
    public User getUserById(Integer id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    @Transactional
    public User findByUserName(String username) {
        User user = null;
        try {
           user = (User)entityManager.createQuery("from User where username='" + username + "'").getSingleResult();
        }
        catch (NoResultException exc) {
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();



    }

    public List<Phone> getPhonesByUserId(Integer userId) {
        return entityManager.createQuery("from Phone where userId=" + userId).getResultList();
    }

    @Override
    @Transactional
    public void deactivateById(Integer userId) {

        User user = getUserById(userId);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // create update
        CriteriaUpdate<User> update = cb.
                createCriteriaUpdate(User.class);

        // set the root class
        Root e = update.from(User.class);

        update.set("active", Boolean.FALSE);

        update.where(cb.equal(e.get("id"), userId));

        int numEntitiesUpdated = entityManager.createQuery(update).executeUpdate();
    }

    @Override
    @Transactional
    public void update(User theUser) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // create update
        CriteriaUpdate<User> update = cb.
                createCriteriaUpdate(User.class);

        // set the root class
        Root e = update.from(User.class);

        if ( (theUser.getName()!=null) && !theUser.getName().isEmpty()) {
            update.set("name", theUser.getName());
        }
        if ( (theUser.getUserName()!=null) && !theUser.getUserName().isEmpty()) {
            update.set("userName", theUser.getUserName());
        }
        if ( (theUser.getPassword()!=null) && !theUser.getPassword().isEmpty()) {
            update.set("password", theUser.getPassword());
        }
        if ( (theUser.getEmail()!=null) && !theUser.getEmail().isEmpty()) {
            update.set("email", theUser.getEmail());
        }
        if ( theUser.getLastLogin()!=null) {
            update.set("lastLogin", theUser.getLastLogin());
        }
        if ( (theUser.getToken()!=null) && !theUser.getToken().isEmpty()) {
            update.set("token", theUser.getToken());
        }
        if ( theUser.getActive()!=null) {
            update.set("active", theUser.getActive());
        }

        update.where(cb.equal(e.get("id"), theUser.getId()));

        entityManager.createQuery(update).executeUpdate();
    }

    @Override
    @Transactional
    public void updatePhone(Phone thePhone) {
        entityManager.merge(thePhone);
    }
}
