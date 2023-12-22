package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long postId;            //글 id (PK)
    private Long userdid;
    private Long tour_id;
    private Long camping_id;
    private String category;
    private String subject;
    private String content;
    private String visibilityl;
    private Long viewcnt;
    private LocalDateTime regDate;
    private String title;
    private String img;
    private User user;   //글 작성자 (FK)

}
