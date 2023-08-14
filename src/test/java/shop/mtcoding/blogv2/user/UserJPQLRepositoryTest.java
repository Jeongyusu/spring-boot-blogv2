package shop.mtcoding.blogv2.user;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserJPQLRepositoryTest {
    // DI할 때 타입으로 찾음 => 싱글톤 패턴이기 때문

    @Autowired
    private UserJPQLRepository userJPQLRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findByUsername_test() {
        User user = userJPQLRepository.findByUsername("ssar");
        System.out.println("테스트 : " + user.getEmail());
    } // rollback

    @Test
    public void findById_test() {
        Optional<User> userOP = userJPQLRepository.mFindById(3);

        if (userOP.isPresent()) {
            User user = userOP.get();
            System.out.println(user.getEmail());
        } else {
            System.out.println("해당 id를 찾을 수 없습니다");
        }
    }

}