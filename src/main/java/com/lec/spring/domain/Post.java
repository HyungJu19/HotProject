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
    private Long boardid;
    private Long tourid;
    private Long campingid;
    private String category;
    private String subject;
    private String content;
    private String visibilityl;
    private LocalDateTime regDate;
    private Long viewcnt;

    private User user;   //글 작성자 (FK)

}
