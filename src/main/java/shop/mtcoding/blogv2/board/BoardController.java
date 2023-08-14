package shop.mtcoding.blogv2.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }


    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO){
        boardService.글쓰기(saveDTO, 1);
        return "redirect:/";
    }

}

