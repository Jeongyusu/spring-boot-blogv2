package shop.mtcoding.blogv2.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 스프링이 실행될 때, BoardRepository의 구현체가 IOC컨테이너에 생성된다.
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
