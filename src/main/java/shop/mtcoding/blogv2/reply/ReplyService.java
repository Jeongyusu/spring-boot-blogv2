package shop.mtcoding.blogv2.reply;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.blogv2.board.Board;
import shop.mtcoding.blogv2.user.User;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO saveDTO, Integer sessionUserId) {
        Reply reply = Reply.builder()
                .comment(saveDTO.getComment())
                .board(Board.builder().id(saveDTO.getBoardId()).build())
                .user(User.builder().id(sessionUserId).build())
                .build();
        replyRepository.save(reply);

    }

    @Transactional
    public void 삭제(Integer id) {
        try {
            replyRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("에러 발생");
        }

    }

}
