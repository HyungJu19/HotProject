package com.lec.spring.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long id;         //  pk
    @ToString.Exclude
    private User user;       // 댓글 작성자( FK )
    @JsonIgnore  //Json 변환시 제외하는 필드
    private Long postId;    // 어느글의 댓글 (FK)

    private String comment;  //댓글 내용

    //java.time.* 객체 변환을 위한 annotation 들
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("regdate")
    private LocalDateTime regDate;  //댓글 날짜
}
