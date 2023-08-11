package shop.mtcoding.blogv2.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2.user.User;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 1.데이터 받기
    // 2.인증체크 (:TODO)
    // 3.유효성검사(1번 과정이 있을 때) (:TODO)
    // 4.핵심로직 호출(서비스)
    // 5.view or data 응답
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) {
        // System.out.println("title : " + saveDTO.getTitle());
        // System.out.println("content : " + saveDTO.getContent());
        Board board = Board.builder()
                .title(saveDTO.getTitle())
                .content(saveDTO.getContent())
                .user(User.builder().id(1).build())
                .build();

        boardRepository.save(board);
        return "redirect:/";
    }

}
