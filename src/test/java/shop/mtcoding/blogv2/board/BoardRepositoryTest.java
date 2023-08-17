package shop.mtcoding.blogv2.board;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv2.user.User;

@DataJpaTest // 모든 Repository, EntitiyManager만 메모리에 뜬다.
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void MfindBy_test() {
        boardRepository.MFindById(5);
    }

    @Test
    public void findById_test() {
        Optional<Board> boardOP = boardRepository.findById(5);
        if (boardOP.isPresent()) {
            System.out.println("테스트 : board가 있습니다.");
        }
    }

    @Test
    public void findAll_test() {
        System.out.println("조회직전");
        List<Board> boardList = boardRepository.findAll();
        System.out.println("조회 후 : Lazy");
        // 행 : 5개 -> 객체 : 5개
        // 각행 : Board (id=1, title=제목1, content=내용1, created_at=날짜, User(id=1))
        // boardRepository.findAll();
        System.out.println(boardList.get(0).getId()); // 1번
        System.out.println(boardList.get(0).getUser().getId()); // 1번
        // Lazy Loading - 지연로딩
        // 연관된 객체에 null을 참조하려고 하면, 조회가 일어남
        System.out.println(boardList.get(0).getUser().getUsername()); // 값이 없으니까(Null) select쿼리를 날려서 값을 가져온다.
    }

    @Test
    public void findAll_paging_test() throws JsonProcessingException {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        Page<Board> boardPG = boardRepository.findAll(pageable);
        ObjectMapper om = new ObjectMapper();
        // ObjectMapper는 boardPG객체의 getter를 호출하면서 json을 만든다.
        String json = om.writeValueAsString(boardPG);
        System.out.println(json);
    }

    @Test
    public void mfindAll_test() {
        boardRepository.mfindAll();
    }

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
