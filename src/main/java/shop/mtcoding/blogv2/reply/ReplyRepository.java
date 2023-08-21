package shop.mtcoding.blogv2.reply;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    // @Query("insert into reply_tb(comment, board_id, user_id) values(:comment,
    // :boardId, :userId)")
    // Optional<Reply> save
}