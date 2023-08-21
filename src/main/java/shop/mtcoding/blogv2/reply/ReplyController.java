package shop.mtcoding.blogv2.reply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.Script;
import shop.mtcoding.blogv2.board.BoardRepository;
import shop.mtcoding.blogv2.board.BoardService;
import shop.mtcoding.blogv2.user.User;

@Controller
public class ReplyController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyRepository ReplyRepository;

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO saveDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        replyService.댓글쓰기(saveDTO, sessionUser.getId());
        return "redirect:/board/" + saveDTO.getBoardId();
    }

    @PostMapping("/reply/{id}/delete")
    public @ResponseBody String delete(@PathVariable Integer id, Integer boardId) {
        // 인증체크
        replyService.삭제(id);
        return Script.href("/board/" + boardId);

    }
}
