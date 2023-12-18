package com.lec.spring.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String username;
    private String nickname;
    @JsonIgnore
    private String password;
    @ToString.Exclude // toString() 결과에서 뺌.
    @JsonIgnore
    private String re_password;  //비밀번호확인 입력 받아오기용 데이터베이스 x
    private String email;

    @JsonIgnore    //제이슨으로 변경안되게 빼는법
    private LocalDateTime regDate;

    // OAuth2
    private String provider;     //어떤 OAuth2 제공자? kakao, naver, google...
    private String providerId;    // provider   내에서 고유 id 값

}
