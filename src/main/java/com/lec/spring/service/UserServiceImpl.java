package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }

    @Override
    public User findByUserName(String username) {
        return null;
    }

    @Override
    public boolean isExist(String username) {
        return false;
    }

    @Override
    public int register(User user) {
        return 0;
    }

    @Override
    public List<Authority> selectAuthoritiesById(Long id) {
        return null;
    }
}
