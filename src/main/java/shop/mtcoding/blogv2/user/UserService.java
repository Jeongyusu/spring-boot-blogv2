package shop.mtcoding.blogv2.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.blogv2.user.UserRequest.LoginDTO;
import shop.mtcoding.blogv2.user.UserRequest.UpdateDTO;


//핵심로직 처리, 트랜잭션 관리, 예외처리
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        User user = User.builder()
                .username(joinDTO.getUsername())
                .password(joinDTO.getPassword())
                .email(joinDTO.getEmail())
                .build();
        
        userRepository.save(user); //em.persist

    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {

        User user = userRepository.findByUsername(loginDTO.getUsername());
        System.out.println("테스트 1: " + user.getUsername());
        // 1. 유저네임 검증
        if (user == null) {
            return null;
        }
        System.out.println("테스트 2: " + user.getUsername());

        // 2. 패스워드 검증
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            return null;
        }
        System.out.println("테스트 3: " + user.getUsername());

        // 3. 로그인 성공
        return user;
    }

    public User 회원정보보기(Integer id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User 회원수정(UserRequest.UpdateDTO updateDTO, Integer id) {
        User user = userRepository.findById(id).get();
        user.setPassword(updateDTO.getPassword());
        return user;
        
    }

}
