//package com.lec.spring.service;
//
//import com.lec.spring.domain.WeatherInfo;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class WeatherServiceImpl implements WeatherService {
//
//
//    @Value("${weather.api.base-Url}")
//    private String baseUrl;
//
//    @Value("${weather.api.key}")
//    private String apiKey;
//
//    public class RegionCodeMap {
//        private static final Map<String, String> regionCodeMap = new HashMap<>();
//
//        static {
//            regionCodeMap.put("서울", "11B00000");
//            regionCodeMap.put("강원도영서", "11D10000");
//            regionCodeMap.put("강원도영동", "11D20000");
//            regionCodeMap.put("대전, 세종, 충청남도", "11C20000");
//            regionCodeMap.put("충청북도", "11C10000");
//            regionCodeMap.put("광주, 전라남도", "11F20000");
//            regionCodeMap.put("전라북도", "11F10000");
//            regionCodeMap.put("대구, 경상북도", "11H10000");
//            regionCodeMap.put("부산, 울산, 경상남도", "11H20000");
//            regionCodeMap.put("제주도", "11G00000");
//        }
//
//        public static String getRegionCode(String region) {
//            return regionCodeMap.get(region);
//        }
//    }
//
//
//    private final RestTemplate restTemplate;
//    public WeatherServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//
//    @Override
//    public WeatherInfo getShortTermForecast(String location, LocalDate startDate, LocalDate endDate) {
//
//        String url = baseUrl + "/VilageFcstInfoService_2.0/getVilageFcst";
//        String baseDate = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        String baseTime = "0600";
//
//        // 파라미터 설정
//        Map<String, String> params = new HashMap<>();
//        params.put("serviceKey", apiKey);
//        params.put("numOfRows", "10");
//        params.put("pageNo", "1");
//        params.put("dataType", "JSON");
//        params.put("base_date", baseDate);
//        params.put("base_time", baseTime);
//        params.put("nx", nx.toString());
//        params.put("ny", ny.toString());
//
//        // API 호출 및 결과 받아오기
//        ResponseEntity<Object> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                Object.class,
//                params
//        );
//
//        // 결과 반환
//        return (WeatherInfo) response.getBody();
//    }
//
//    @Override
//    public WeatherInfo getMidTermWeather(String location, LocalDate startDate, LocalDate endDate) {
//
//        String url = baseUrl + "/MidFcstInfoService/getMidLandFcst";
//        String tmFc = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))+ "0600";
//
//        // 파라미터 설정
//        Map<String, String> params = new HashMap<>();
//        params.put("serviceKey", apiKey);
//        params.put("numOfRows", "10");
//        params.put("pageNo", "1");
//        params.put("dataType", "JSON");
//        params.put("regId", );
//        params.put("tmFc", tmFc);
//
//
//        // API 호출 및 결과 받아오기
//        ResponseEntity<Object> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                Object.class,
//                params
//        );
//
//        // 결과 반환
//        return (WeatherInfo) response.getBody();
//    }
//
//    @Override
//    public WeatherInfo getMidTermTemperature(String location, LocalDate startDate, LocalDate endDate) {
//
//        String url = baseUrl + "/MidFcstInfoService/getMidLandFcst";
//        String tmFc = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))+ "0600";
//
//        // 파라미터 설정
//        Map<String, String> params = new HashMap<>();
//        params.put("serviceKey", apiKey);
//        params.put("numOfRows", "10");
//        params.put("pageNo", "1");
//        params.put("dataType", "JSON");
//        params.put("regId", );
//        params.put("tmFc", tmFc);
//
//
//        // API 호출 및 결과 받아오기
//        ResponseEntity<Object> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                Object.class,
//                params
//        );
//
//        // 결과 반환
//        return (WeatherInfo) response.getBody();
//    }
//    }
//}
