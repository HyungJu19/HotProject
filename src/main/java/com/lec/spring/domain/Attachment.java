package com.lec.spring.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
    private Long id;  // PK
    private Long postId;
    private String sourcename; // 원본 파일명
    private String filename;   // 저장된 파일명 (rename 된 파일명)
    private boolean isImage;   // 이미지 여부



}








