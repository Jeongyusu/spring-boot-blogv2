package shop.mtcoding.blogv2.board;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.blogv2.user.User;

@DataJpaTest // 모든 Repository, EntitiyManager만 메모리에 뜬다.
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void save_test() {
        // 비영속 객체
        Board board = Board.builder()
                .title("제목6")
                .content("내용6")
                .user(User.builder().id(1).build())
                .build();

        // board.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        // 영속성 객체
        boardRepository.save(board); // insert가 자동으로 실행되고 영속화가 이루어진다. 그리고 데이터가 DB와 동기화된다.
        System.out.println(board.getId()); // 이 단계에서는 board는 영속성객체

    }

}
