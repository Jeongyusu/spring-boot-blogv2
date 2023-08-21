package shop.mtcoding.blogv2.user;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueryRepository {

    @Autowired
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findById(Integer id) {
        return em.find(User.class, id);

    }

    public User findByUsername(String username) {
        Query query = em.createQuery("select u from User u where u.username = :usernmae", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }
}
