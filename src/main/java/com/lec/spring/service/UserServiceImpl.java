/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.User;
import com.lec.spring.domain.role;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }



    @Override
    public boolean isExist(String username) {
        User user = findByUsername(username);
        return (user != null ) ? true : false;
    }

    @Override
    public boolean isExistNick(String nickname) {
        User user = findByNickname(nickname);
        return (user != null ) ? true : false;
    }

    @Override
    public boolean isExistEmail(String email) {
        User user = findByEmail(email);
        return (user != null ) ? true : false;
    }


    @Override
    public int signup(User user) {
        //DB 에는 회원 username 을 대문자로 저장
        user.setUsername(user.getUsername().toLowerCase());

        // password 는 암호화 해서 저장.  PasswordEncoder 객체 사용
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user); //새로운 회원 (User) 저장. id 값 받아옴.

        //신규 회원은 ROLE_MEMBER 권한을 부여하기
        role auth = authorityRepository.findByName("ROLE_MEMBER");

        Long user_id = user.getUid();
        Long auth_id = auth.getRole_id();
        authorityRepository.addAuthority(auth_id, user_id);

        return 1;
    }

    @Override
    public List<role> selectAuthoritiesById(Long uid) {//특정 회원 권한 가져오기
        User user = userRepository.findById(uid);

        return authorityRepository.findByUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Long findByuid(String username) {

        return userRepository.findByuid(username);
    }

    // 이 메서드는 실제 데이터베이스에서 사용자의 관광지 ID를 조회하는 로직을 담게 됩니다.
    public Integer getTourIdByUserId(Integer userId) {
        // 여기에 데이터베이스에서 사용자의 관광지 ID를 조회하는 로직을 구현합니다.
        // 예를 들어, userId를 이용해 관련 정보를 데이터베이스에서 조회하고 그에 맞는 tourId를 반환하는 코드를 작성합니다.
        // 임시로 1을 반환하는 코드를 작성하겠습니다.
        return 1;
    }

    public void likeTour(Long userId, Long tourId) {
        // 좋아요 추가 로직

        userRepository.likesave(userId, tourId);
    }

    public void unlikeTour(Long uid, Long id) {
        // 좋아요 취소 로직
        System.out.println(uid);
        System.out.println(id);

        userRepository.deleteById(uid,id);
    }

    public void likeCamping(Long uid, Long campingid){

        userRepository.camlikesave(uid,campingid);
    }


    public void unlikeCamping(Long uid, Long campingid){

        userRepository.deleteBycamId(uid,campingid);
    }
}




