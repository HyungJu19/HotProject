package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long postId;            //글 id (PK)
    private Long tour_id;
    private Long camping_id;
    private String category;
    private String subject;
    private String img;
    private String content;
    private String visibility;
    private LocalDateTime regDate;
    private Long viewcnt;
    private String title;

    private User user;   //글 작성자 (FK)


    // 첨부파일
    @ToString.Exclude
    @Builder.Default
    private List<Attachment> fileList = new ArrayList<>();

}
