package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportsData {
    private Long id;            // ID
    private String title;       // 제목
    private String zipcode;     //우편번호
    private String addr1;       // 주소
    private String areacode;    // 지역 코드
    private String contentid;   // 컨텐츠 ID
    private String contenttypeid; // 컨텐츠 타입 ID
    private String firstimage;  // 첫 번째 이미지 URL
    private String mapx;        // 지도 X 좌표
    private String mapy;        // 지도 Y 좌표
    private String sigungucode; // 시군구 코드
    private String cat1;   //대분류
    private String cat2;   //중분류
    private String cat3;   //대분류




}
