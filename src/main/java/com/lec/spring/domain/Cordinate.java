package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cordinate {
    private String doNm;
    private Double mapX;
    private Double mapY;
    private String areacode;
    private String mapx;
    private String mapy;

    private String regld;
    private String mx;
    private String my;
}
