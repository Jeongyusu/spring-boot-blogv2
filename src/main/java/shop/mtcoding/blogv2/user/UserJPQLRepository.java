package shop.mtcoding.blogv2.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJPQLRepository extends JpaRepository<User, Integer> {

    // JPQL은 insert, update, Delete를 지원하지 않는다.
    @Query(value = "select u from User u where u.id = :id")
    Optional<User> mFindById(@Param("id") Integer id);

    // executeQuery
    @Query(value = "select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Modifying // executeUpdate
    @Query(value = "insert into user_tb(created_at, email, password, username) values(now(), :email, :password, :username)", nativeQuery = true)
    void mSave(@Param("username") String username, @Param("password") String password, @Param("email") String email);

}
