package shop.mtcoding.blogv2.board;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

public class BoardResponse {

    @Getter
    @Setter
    public static class ListDTO {
        private Page<Board> page;
        private Integer nextPage;
        private Integer prevPage;
    }

}
