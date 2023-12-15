/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.role;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
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
    public String findId(HttpServletResponse response, String email) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String uid = userRepository.findId(email);

        if (uid == null) {
            out.println("<script>");
            out.println("alert('가입된 아이디가 없습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        } else {
            return uid;
        }
    }


}
