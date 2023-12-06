package com.lec.spring.service;

import com.lec.spring.domain.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {


    @Value("${weather.api.base-url}")
    private String baseUrl;

    @Value("${weather.api.key}")
    private String apiKey;


    private final RestTemplate restTemplate;
    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public WeatherInfo getShortTermForecast(String location, LocalDate startDate, LocalDate endDate) {

        String url = baseUrl + "/getVilageFcst";
        String baseDate = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = "0600";

        // 파라미터 설정
        Map<String, String> params = new HashMap<>();
        params.put("serviceKey", apiKey);
        params.put("numOfRows", "10");
        params.put("pageNo", "1");
        params.put("dataType", "JSON");
        params.put("base_date", baseDate);
        params.put("base_time", baseTime);
        params.put("nx", nx.toString());
        params.put("ny", ny.toString());

        // API 호출 및 결과 받아오기
        ResponseEntity<Object> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class,
                params
        );

        // 결과 반환
        return (WeatherInfo) response.getBody();
    }

    @Override
    public WeatherInfo getMidTermWeather(String location, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public WeatherInfo getMidTermTemperature(String location, LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
