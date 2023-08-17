package shop.mtcoding.blogv2.user;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserQueryRepository.class)
@DataJpaTest // JpaRepository만 Ioc메모리에 올려준다.
public class UserQueryRepositoryTest {
    
    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test(){
        User user = User.builder()
                .username("love")
                .password("love")
                .email("love@naver.com")
                .build();
            
        userQueryRepository.save(user);
    }

    // 1차 캐시
    @Test
    public void findById_test(){
        System.out.println("1. pc는 비어있다.");
        userQueryRepository.findById(1);
        System.out.println("2. pc의 user1은 영속화 되어있다.");
        em.clear();
        userQueryRepository.findById(1);
        System.out.println("?????????");
    }

    @Test
    public void update_test() {
        // Jpa update 알고리즘
        // 1. 업데이트 할 객체를 영속화
        // 2. 객체 상태 변경
        // 3. em.flush() or @Transactional 정상 종료

        User user = userQueryRepository.findById(1);
        user.setEmail("ssarmango@nate.com");
        em.flush();
    }
}
