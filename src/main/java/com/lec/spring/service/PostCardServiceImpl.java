package com.lec.spring.service;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.PostCardData;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PostCardRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.time.LocalDateTime;

@Service
public class PostCardServiceImpl implements PostCardService {

    private PostCardRepository postCardRepository;


    @Autowired
    public PostCardServiceImpl(SqlSession sqlSession) {
        postCardRepository = sqlSession.getMapper(PostCardRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }


//    @Override
//    public int postCardDataSave(PostCardData postCardData) {
//
//        postCardRepository.postCardSave(postCardData);
//        return 1;
//
//    }

    @Override
    public String postCardDataSave(HttpServletResponse response, PostCardData postCardData) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        postCardRepository.postCardSave(postCardData);

        out.println("<script>");
        out.print("alert('저장 완료.');");
//        out.println("location.href='../postCard/main';");
        out.println("history.back();");
        out.println("</script>");
        out.close();

        return "저장 완료";

    }


}
