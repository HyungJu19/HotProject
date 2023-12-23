package com.lec.spring.service;

import com.lec.spring.domain.PostCardData;
import com.lec.spring.domain.User;
import jakarta.servlet.http.HttpServletResponse;


public interface PostCardService {

//    int postCardDataSave(PostCardData postCardData);

    String postCardDataSave(HttpServletResponse response, PostCardData postCardData) throws Exception;

}
