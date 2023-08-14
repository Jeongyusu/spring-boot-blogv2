package shop.mtcoding.blogv2.user;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserQueryRepository.class)
@DataJpaTest // JPaRepository를 메모리에 올려준다.
public class UserQueryRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserQueryRepository userQueryRepository;

    // persist(영속화)
    @Test
    public void save_test() {
        User user = User.builder()
                .username("love")
                .password("1234")
                .email("love@nate.com")
                .build();

        userQueryRepository.save(user); // 영속화
    }

    // 1차 캐시
    @Test
    public void findById_test() {
        // pc는 비어있다.

        System.out.println("1. pc는 비어있다");
        // 1번 객체가 있는지 확인 => 없으니까 select 쿼리 발동 =>
        userQueryRepository.findById(1);
        System.out.println("2. pc의 user1은 영속화 되어있다.");
        em.clear();
        userQueryRepository.findById(1);
        // pc는 user 1의 객체가 영속화 되어있다.

    }

    @Test
    public void update_test() {
        // JPA update 알고리즘
        // 1. 업데이트 할 객체를 영속화
        // 2. 객체 상태 변경
        // 3. em.flush() or @Transactional 정상 종료
        // 4. @Test 환경에서는 em.flush()

        User user = userQueryRepository.findById(1);
        user.setEmail("ssarmango@nate.com");
        em.flush();

    }

}
