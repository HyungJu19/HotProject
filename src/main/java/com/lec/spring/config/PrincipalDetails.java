package com.lec.spring.config;

import com.lec.spring.domain.role;
import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrincipalDetails implements UserDetails {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 로그인한 사용자 정보
    private User user;

    public User getUser() {
        return user;
    }

    public PrincipalDetails(User user){
        System.out.println("UserDetails(user) 생성: " + user);
        this.user = user;
    }

    //OAuth 로그인용 생성자
    //TODO


    // 해당 User 의 '권한(들)'을 리턴
    // 현재 로그인한 사용자의 권한정보가 필요할때마다 호출된다. 혹은 필요할때마다 직접 호출해 사용할수도 있다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities() 호출");

        Collection<GrantedAuthority> collect = new ArrayList<>();

        // DB 에서 user 의 권한(들) 읽어오기
        List<role> list = userService.selectAuthoritiesById(user.getUid());

        for(role auth : list){
            collect.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return auth.getRole_name();
                }

                // thymeleaf 등에서 확인 활용하기 위하 문자열 (학습목적)
                @Override
                public String toString() {
                    return auth.getRole_name();
                }
            });
        }

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //-------------------------------------------------------------
    // OAuth2User 를 implement 하면 구현할 메소드
    //TODO
}
