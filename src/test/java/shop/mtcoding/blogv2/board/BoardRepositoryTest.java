package shop.mtcoding.blogv2.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.ToString;
import shop.mtcoding.blogv2.user.User;


@DataJpaTest // 모든 Repository, EntityManager
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void mfindAll_test() {
        boardRepository.mFindAll();

    }
    
    @Test
    public void findAll_test(){
        System.out.println("조회 직전");
        List<Board> boardList = boardRepository.findAll();
        System.out.println("조회 후 : Lazy");
        // 행: 5개 -> 객체 : 5개
        // 각행 : Board (id=1, title=제목1, content=내용1, created_at=날짜, User(id=1))

        System.out.println(boardList.get(0).getId()); //1번 (조회X)
        System.out.println("user id: " + boardList.get(0).getUser().getId());
        System.out.println("===========================================");
        System.out.println("User 내부" + boardList.get(1).getUser()); // 1번(조회X)

        // Lazy Loading - 지연로딩
        // 연관된 객체에 null을 참조하려고 하면 조회가 일어남(조회o)
        System.out.println(boardList.get(0).getUser().getUsername()); // null -> ssar
        System.out.println(boardList.get(3).getUser().getUsername());

    }

    @Test
    public void save_test(){
        // 비영속 객체
        Board board = Board.builder()
            .title("제목6")
            .content("내용6")
            .user(User.builder().id(1).build())
            .build();

        System.out.println("나 실행됨");
        // 영속 객체
        boardRepository.save(board); //inset가 자동으로 실행됨

        // 디비데이터와 동기화됨
        System.out.println(board.getId());
    }

    
}
