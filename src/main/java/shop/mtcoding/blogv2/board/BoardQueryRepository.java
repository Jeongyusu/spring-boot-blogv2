package shop.mtcoding.blogv2.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public class BoardQueryRepository {

    @Autowired
    private EntityManager em;

    List<Board> mFindByAll(@Param("keyword") String keyword) {
        Query query = em.createNativeQuery("select * from board_tb where title like :keyword", Board.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();

    }

}
