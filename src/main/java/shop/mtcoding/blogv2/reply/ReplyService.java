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
        // insert into reply_tb(comment, board_id, user_id) values(?, ?, ?)
        // 1. board id가 존재하는지 유무
        Board board = Board.builder().id(saveDTO.getBoardId()).build();
        User user = User.builder().id(sessionUserId).build();
        Reply reply = Reply.builder()
                .comment(saveDTO.getComment())
                .board(board)
                .user(user)
                .build();
        replyRepository.save(reply); // entity : reply 객체

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
