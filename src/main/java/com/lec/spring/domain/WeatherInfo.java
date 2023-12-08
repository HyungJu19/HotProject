package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherInfo {


    // 단기예보(당일 후 3일까지의 데이터)
    private String category;    // 단기예보조회 자료구분문자
    private String fcstValue;   // 예보값

    // 중기기상예보(당일 후 3~10일 데이터)
    private String rnSt3Am;     // 중기육상예보조회 3일 후 오전 강수 확률 날씨예보
    private String rnSt3Pm;     // 중기육상예보조회 3일 후 오후 강수 확률 날씨예보
    private String rnSt4Am;     // 중기육상예보조회 4일 후 오전 강수 확률 날씨예보
    private String rnSt4Pm;     // 중기육상예보조회 4일 후 오후 강수 확률 날씨예보
    private String rnSt5Am;     // 중기육상예보조회 5일 후 오전 강수 확률 날씨예보
    private String rnSt5Pm;     // 중기육상예보조회 5일 후 오후 강수 확률 날씨예보
    private String rnSt6Am;     // 중기육상예보조회 6일 후 오전 강수 확률 날씨예보
    private String rnSt6Pm;     // 중기육상예보조회 6일 후 오후 강수 확률 날씨예보
    private String rnSt7Am;     // 중기육상예보조회 7일 후 오전 강수 확률 날씨예보
    private String rnSt7Pm;     // 중기육상예보조회 7일 후 오후 강수 확률 날씨예보
    private String rnSt8;       // 중기육상예보조회 8일 후 강수 확률 날씨예보
    private String rnSt9;       // 중기육상예보조회 9일 후 강수 확률 날씨예보
    private String rnSt10;      // 중기육상예보조회 10일 후 강수 확률 날씨예보
    private String wf3Am;       // 중기육상예보조회 3일 후 오전 날씨예보
    private String wf3Pm;       // 중기육상예보조회 3일 후 오후 날씨예보
    private String wf4Am;       // 중기육상예보조회 4일 후 오전 날씨예보
    private String wf4Pm;       // 중기육상예보조회 4일 후 오후 날씨예보
    private String wf5Am;       // 중기육상예보조회 5일 후 오전 날씨예보
    private String wf5Pm;       // 중기육상예보조회 5일 후 오후 날씨예보
    private String wf6Am;       // 중기육상예보조회 6일 후 오전 날씨예보
    private String wf6Pm;       // 중기육상예보조회 6일 후 오후 날씨예보
    private String wf7Am;       // 중기육상예보조회 7일 후 오전 날씨예보
    private String wf7Pm;       // 중기육상예보조회 7일 후 오후 날씨예보
    private String wf8;         // 중기육상예보조회 8일 후 날씨예보
    private String wf9;          // 중기육상예보조회 9일 후 날씨예보
    private String wf10;        // 중기육상예보조회 10일 후 날씨예보

    // 중기기온예보
    private String taMin3;      // 중기기온조회 3일후 예상 최저기온
    private String taMax3;      // 중기기온조회 3일후 예상 최고기온
    private String taMin4;      // 중기기온조회 4일후 예상 최저기온
    private String taMax4;      // 중기기온조회 4일후 예상 최고기온
    private String taMin5;      // 중기기온조회 5일후 예상 최저기온
    private String taMax5;      // 중기기온조회 5일후 예상 최고기온
    private String taMin6;      // 중기기온조회 6일후 예상 최저기온
    private String taMax6;      // 중기기온조회 6일후 예상 최고기온
    private String taMin7;      // 중기기온조회 7일후 예상 최저기온
    private String taMax7;      // 중기기온조회 7일후 예상 최고기온
    private String taMin8;      // 중기기온조회 8일후 예상 최저기온
    private String taMax8;      // 중기기온조회 8일후 예상 최고기온
    private String taMin9;      // 중기기온조회 9일후 예상 최저기온
    private String taMax9;      // 중기기온조회 9일후 예상 최고기온
    private String taMin10;      // 중기기온조회 10일후 예상 최저기온
    private String taMax10;      // 중기기온조회 10일후 예상 최고기온


}