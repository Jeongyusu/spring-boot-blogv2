package shop.mtcoding.blogv2.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPQLRepository extends JpaRepository<User, Integer>{

    // @Query(value = "select u from User u where ")
    
}
