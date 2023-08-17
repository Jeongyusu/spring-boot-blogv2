package shop.mtcoding.blogv2.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface UserJPQLRepository extends JpaRepository<User, Integer>{

    @Query(value = "select u from User u where u.id = :id")
    Optional<User> mFindById(@Param("id") Integer id);

    @Query(value = "select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    // insert, update, delete는 jpql사용하지 못함
    @Modifying
    @Query(value = "insert into user_tb(created_at, email, password, username) values(now(), :email, :password, :username)", nativeQuery = true)
    void mSave(@Param("username") String username, @Param("email") String email);
    
    
}
