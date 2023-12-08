package com.lec.spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {


    @Value("${weather.api.base-Url}")
    private String baseUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final CoordinatesService coordinatesService;
    private final RestTemplate restTemplate;


    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate, CoordinatesService coordinatesService) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        this.coordinatesService = coordinatesService;
    }

    @Override
    public WeatherInfo getShortTermForecast(String location, LocalDate startDate, LocalDate endDate) {

        String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
        String baseDate = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = "0500";

        // 파라미터 설정
        Map<String, String> params = new LinkedHashMap<>();
        params.put("serviceKey", apiKey);
        params.put("numOfRows", "10");
        params.put("pageNo", "1");
        params.put("base_date", baseDate);
        params.put("base_time", baseTime);

        CoordinatesService.Coordinates coordinates = coordinatesService.getCoordinates(location);
        if (coordinates != null) {
            params.put("nx", String.valueOf(coordinates.getLatitude()));
            params.put("ny", String.valueOf(coordinates.getLongitude()));
        } else {
            throw new IllegalArgumentException("해당 지역의 좌표를 찾을 수 없습니다.");
        }
        params.put("dataType", "JSON");

// URL 조합
        String fullUrl = url + "?" + params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        System.out.println("Request URL: " + fullUrl);


        // API 호출 및 결과 받아오기
        ResponseEntity<WeatherInfo> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                WeatherInfo.class, // 제네릭 타입을 WeatherInfo로 변경
                params
        );

        // 결과를 WeatherInfo 객체로 반환
        return response.getBody();
    }


    @Override
    public WeatherInfo getMidTermWeather(String location, LocalDate startDate, LocalDate endDate) {

        String url = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst";
        String tmFc = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "1800";
        String regId = getMidTermWeatherRegId(location);

        // 파라미터 설정
        Map<String, String> params = new LinkedHashMap<>();
        params.put("serviceKey", apiKey);
        params.put("pageNo", "1");
        params.put("numOfRows", "10");
        params.put("dataType", "JSON");
        params.put("regId", regId);
        params.put("tmFc", tmFc);

        // API 호출 및 결과 받아오기
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String.class,
                params
        );

        // 결과 문자열을 WeatherInfo 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.getBody(), WeatherInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 WeatherInfo 객체로 변환하는 데 실패했습니다.", e);
        }
    }

    @Override
    public WeatherInfo getMidTermTemperature(String location, LocalDate startDate, LocalDate endDate) {

        String url = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa";
        String tmFc = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "0600";
        String regId = getMidTermTemperatureRegId(location);

        // 파라미터 설정
        Map<String, String> params = new LinkedHashMap<>();
        params.put("serviceKey", apiKey);
        params.put("numOfRows", "10");
        params.put("pageNo", "1");
        params.put("dataType", "JSON");
        params.put("regId", regId);
        params.put("tmFc", tmFc);

        // API 호출 및 결과 받아오기
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String.class,
                params
        );

        // 결과 문자열을 WeatherInfo 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.getBody(), WeatherInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 WeatherInfo 객체로 변환하는 데 실패했습니다.", e);
        }
    }

    private String getMidTermWeatherRegId(String location) {
        switch (location) {
            case "서울", "인천", "경기":
                return "11B00000";
            case "강원":
                return "11D10000";
            case "대전", "세종", "충남":
                return "11C20000";
            case "충북":
                return "11C10000";
            case "광주", "전남":
                return "11F20000";
            case "전북":
                return "11F10000";
            case "대구", "경북":
                return "11H10000";
            case "부산", "울산", "경남":
                return "11H20000";
            case "제주":
                return "11G00000";
            default:
                throw new IllegalArgumentException("해당 지역의 중기예보 regId를 찾을 수 없습니다.");
        }
    }

    private String getMidTermTemperatureRegId(String location) {
        // 중기 기온예보 regId 설정
        switch (location) {
            case "서울":
                return "11B10101";
            case "인천":
                return "11B20201";
            case "경기":
                return "11B20601";
            case "강원":
                return "11D10301";
            case "충남":
                return "11C20101";
            case "세종":
                return "11C20404";
            case "대전":
                return "11C20401";
            case "전북":
                return "21F10501";
            case "충북":
                return "11C10301";
            case "광주":
                return "11F20501";
            case "전남":
                return "21F20801";
            case "대구":
                return "11H10701";
            case "경북":
                return "11H10501";
            case "경남":
                return "11H20301";
            case "부산":
                return "11H20201";
            case "울산":
                return "11H20101";
            case "제주":
                return "11G00401";
            default:
                throw new IllegalArgumentException("해당 지역의 중기 기온예보 regId를 찾을 수 없습니다.");
        }
    }
}

