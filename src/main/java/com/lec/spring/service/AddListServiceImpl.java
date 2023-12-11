package com.lec.spring.service;

import com.lec.spring.domain.TouristData;
import com.lec.spring.domain.User;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddListServiceImpl implements AddListService {
    private TouristRepository touristRepository;
    private UserRepository userRepository;

    @Autowired
    public AddListServiceImpl(SqlSession sqlSession) {
        touristRepository = sqlSession.getMapper(TouristRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
    }
    @Override
    public int addDestination(TouristData touristData) {
        User user = U.getLoggedUser();
        // 여행지 추가 로직 구현

        user = userRepository.findById(user.getUid());
        touristData.setUser(user);
        return touristRepository.save(touristData);

        // 실제로는 데이터를 저장하는 등의 작업을 수행해야 함



    }
}
