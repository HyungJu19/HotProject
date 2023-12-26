package com.lec.spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CampingData {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long camping_id;        // ID
    private String facltNm;         // 캠핑장명
    private String intro;           // 캠핑장소개
    private String induty;          // 업종
    private String lctCl;           // 입지구분
    private String area;            // 도
    private String sigunguNm;       // 시군구
    private String addr1;           // 주소
    private Double mapX;              // 경도
    private Double mapY;              // 위도
    private String tel;             // 전화번호
    private String operPdCl;        // 운영기간
    private String operDeCl;        // 운영일
    private String tourEraCl;       // 여행시기
    private String firstImageUrl;   // 대표이미지
    private String posblFcltyCl;    // 주변이용가능시설
    private String themaEnvrnCl;    // 테마환경
    private String animalCmgCl;     // 반려동물 동행여부
    private String contentId;   // 콘텐츠id
}
