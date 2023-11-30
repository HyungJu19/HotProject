/**
 * #민호
 */

package com.lec.spring.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TouristApiResponse {
    private Response response;

    // getters and setters

    @Data
    public static class Response {
        private Body body;
        // getters and setters

        @Data
        public static class Body {
            private Items items;
            private int totalCount; // totalCount 필드 추가

            // getters and setters


            @Data
            public static class Items {

                private List<Item> item;
                // getters and setters

                @Data
                public static class Item {
                    private String title;
                    private String addr1;
                    private String zipcode;
                    private String areacode;
                    private String contentid;
                    private String contenttypeid;
                    private String firstimage;
                    private String mapx;
                    private String mapy;
                    private String sigungucode;
                    private String cat1;   //대분류
                    private String cat2;   //중분류
                    private String cat3;   //대분류
                    // 기타 필요한 필드
                    // getters and setters
                }
            }
        }
    }
}