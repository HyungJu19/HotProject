package com.lec.spring.util;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class U {
    public static User getLoggedUser(){
        // 현재 로그인 한 사용자
        PrincipalDetails userDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return user;
    }
}
