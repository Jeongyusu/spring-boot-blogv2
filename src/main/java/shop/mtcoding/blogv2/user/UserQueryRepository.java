package shop.mtcoding.blogv2.user;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueryRepository {

    @Autowired
    private EntityManager em;

    public void save(User user) {
        em.persist(user); // 영속화 (영속성 컨텍스트)
    }

    public User findById(Integer id) {
        // 영속성 컨텍스트에서 찾는다. em.find는 쓸 일이 없지만 원리 이해용으로 실습.
        return em.find(User.class, id);
        //테스트용

    }

}