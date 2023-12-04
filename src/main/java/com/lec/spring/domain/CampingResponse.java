package com.lec.spring.domain;

import lombok.Data;

import java.util.List;

@Data
public class CampingResponse {
    private Response response;

    // getters and setters

    @Data
    public static class Response{
        private Body body;
        // getters and setters

        @Data
        public static class Body{
        private Items items;
        private int totalCount; // totalCount 필드 추가

            // getters and setters
            @Data
            public static class Items{
                private List<Item> item;
                // getters and setters

                @Data
                public static class Item{
                    private String facltNm;
                    private String lineintro;       // 한줄소개
                    private String intro;           // 캠핑장소개
                    private String induty;          // 업종
                    private String lctCl;           // 입지구분
                    private String doNm;            // 도
                    private String sigunguNm;       // 시군구
                    private String addr1;           // 주소
                    private Double mapX;            // 경도
                    private Double mapY;            // 위도
                    private String tel;             // 전화번호
                    private String operPdCl;        // 운영기간
                    private String operDeCl;        // 운영일
                    private String tourEraCl;       // 여행시기
                    private String firstImageUrl;   // 대표이미지
                    private String posblFcltyCl;    // 주변이용가능시설
                    private String themaEnvrnCl;    // 테마환경
                    private String animalCmgCl;     // 반려동물 동행여부
                    private String contentId;       // 콘텐츠id
                    // 기타 필요한 필드
                    // getters and setters
                }
            }
        }
    }
}
