package shop.mtcoding.blogv2.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//컨트롤러의 책임: 요청처리 (유효성검사 등도 요청처리에 포함) 
@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Board board = boardService.상세보기(id); // 엔티티 노출하는 것은 별로 좋지 않음. 나중에 DTO에 옮겨주는 것이 필요
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request) {
        Page<Board> boardPG = boardService.게시글목록보기(page);
        request.setAttribute("boardPG", boardPG);
        request.setAttribute("prevPage", boardPG.getNumber() - 1);
        request.setAttribute("nextPage", boardPG.getNumber() + 1);
        return "index";

        // BoardResponse.ListDTO listDTO = new BoardResponse.ListDTO();
        // listDTO.setPage(boardPG);
        // listDTO.setPrevPage(boardPG.getNumber() - 1);
        // listDTO.setNextPage(boardPG.getNumber() + 1);
    }

    @GetMapping("/test")
    public @ResponseBody Page<Board> test(@RequestParam(defaultValue = "0") Integer page) {
        Page<Board> boardPG = boardService.게시글목록보기(page);
        return boardPG;

    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 1. 데이터 받기 (V)
    // 2. 인증체크 (:TODO)
    // 3. 유효성 검사 (:TODO)
    // 4. 핵심로직 호출(서비스)
    // 5. view or data 응답
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) {
        boardService.글쓰기(saveDTO, 1);
        return "redirect:/";
    }
}