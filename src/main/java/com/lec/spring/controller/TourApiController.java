package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourApiController {

    @Value("${google.maps.api-key}")
    private String apiKey;


    @Value("${kakao.map.api-key}")
    private String apiKeyKakao;

    @GetMapping("/api-key")
    public String getApiKey() {
        return apiKey;
    }



    @GetMapping("/api-keyKakao")
    public String getKakaoMap() {
        return apiKeyKakao;
    }
}



