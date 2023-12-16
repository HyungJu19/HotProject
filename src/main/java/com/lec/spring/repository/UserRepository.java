/**
 * #민호
 */


package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserRepository {


    // 특정 id (PK) 의 user 리턴
    User findById(Long uid);


    // 특정 username 의 user 리턴
    User findByUsername(String username);

    User findByNickname(String nickname);

    User findByEmail(String email);


    // 새로운 User 등록
    int save(User user);
    int likesave(Long uid,Long id);

    Integer findTourIdByUserId(@Param("userId") Integer userId);

    // User 정보 수정
    int update(User user);

    User getUserByUsername(String username);

    //user id 찾기
    Long findByuid(String username);


    int deleteById(Long uid , Long id);


}
