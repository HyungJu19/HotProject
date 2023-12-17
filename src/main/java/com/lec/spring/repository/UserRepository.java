/**
 * #민호
 */


package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository {

    // 특정 id (PK) 의 user 리턴
    User findById(Long uid);


    // 특정 username 의 user 리턴
    User findByUsername(String username);

    User findByNickname(String nickname);

    User findByEmail(String email);


    // 새로운 User 등록
    int save(User user);



    // User 정보 수정
    int update(User user);




    // 아이디 찾기
    String findId(String email) throws Exception;

    // 비밀번호 변경
    @Transactional
    int updatepw(User user) throws Exception;


}
