package shop.mtcoding.blogv2.board;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import shop.mtcoding.blogv2.user.User;

@Service
public class BoardService {

    @Transactional
    public void 회원가입(BoardRequest.SaveDTO saveDTO, User sessionUser) {

    }

}
