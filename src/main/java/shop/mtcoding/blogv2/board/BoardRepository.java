package shop.mtcoding.blogv2.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * save(), findById(), findAll(), count(), deleteById()
 */

//Board, Integer 에서 Integer는 Board의 프라이머리키의 타입을 말한다.
//스프링이 실행될 때, BoardRepository의 구현체가 IOC 컨테이너에 생성된다.
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
