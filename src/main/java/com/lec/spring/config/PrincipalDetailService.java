package com.lec.spring.config;

import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername(" + username + ") 호출");
        // DB 조회
        User user = userService.findByUsername(username);


    // 해당 username 의 User 가 DB 에 있었다면
    //UserDetails 를 생성하여 리턴
        if(user != null){
        PrincipalDetails userDetails = new PrincipalDetails(user);
        userDetails.setUserService(userService);
        return userDetails;

    }

    // 해당 username 이 User가없다면?
    // UsernameNotFoundException 을 throw 해주어야 한다.
        throw  new UsernameNotFoundException(username);

    //* 주의 여기서 null 리턴하면 예외발생

}
}
