package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class role {

    private Long user_id;   //pk
    private String role_name; // 권한명 ex) "ROLE_MEMBER", "ROLE_ADMIN"
}
