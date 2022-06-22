package app.dao;

import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void removeUserById(long id) {
        Query query = entityManager.createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public User findUserById(int id) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.id = " + id, User.class);
        return q.getSingleResult();
    }

    @Override
    public void updateUser(long id, User user) {
        Query query = entityManager.createQuery("update User set firstName = :firstName, lastName = :lastName, " +
                "age = :age where id = :id");
        query.setParameter("id", id);
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("age", user.getAge());
        query.executeUpdate();

    }
}
