package com.lec.spring.service;

import com.lec.spring.domain.*;
import com.lec.spring.repository.TouristRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristServiceImpl implements TouristService {


    private RestTemplate restTemplate;
    private TouristRepository touristRepository;

    @Value("${custom.api.key}")
    private String apiKey;

    @Autowired
    public TouristServiceImpl(SqlSession sqlSession) {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(50000); // 연결 타임아웃 10초
        clientHttpRequestFactory.setReadTimeout(50000); // 읽기 타임아웃 10초
        restTemplate = new RestTemplate();
        touristRepository = sqlSession.getMapper(TouristRepository.class);
    }

    
    //관광지
    @Override
    public List<TouristData> fetchTouristSpots(String areacode) {
        List<TouristData> allSpots = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 100; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;

        do {
            String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=ETC&MobileApp=AppTest&ServiceKey=" + apiKey + "&listYN=Y&arrange=D&contentTypeId=12&areaCode=" + areacode + "&sigunguCode=&cat1=&cat2=&cat3=&_type=json";
            ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

            if (pageNo == 1) {
                totalCount = response.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<TouristData> spots = response.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> item.getFirstimage() != null && !item.getFirstimage().isEmpty())
                    .filter(item -> item.getAddr1() != null && !item.getAddr1().isEmpty())
                    .map(item -> new TouristData(
                            null,
                            item.getTitle(),
                            item.getZipcode(),
                            item.getAddr1(),
                            item.getAreacode(),
                            item.getContentid(),
                            item.getContenttypeid(),
                            item.getFirstimage(),
                            item.getMapx(),
                            item.getMapy(),
                            item.getSigungucode(),
                            item.getCat1(),
                            item.getCat2(),
                            item.getCat3(),
                            0
                    ))
                    .collect(Collectors.toList());

            allSpots.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return allSpots;
    }

    
    //음식점
    @Override
    public List<RestaurantData> fetchRestaurantSpots(String areacode) {
        List<RestaurantData> restaurantSpots = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 100; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;

        do {
            String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=ETC&MobileApp=AppTest&ServiceKey=" + apiKey + "&listYN=Y&arrange=D&contentTypeId=39&areaCode=" + areacode + "&sigunguCode=&cat1=&cat2=&cat3=&_type=json";
            ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

            if (pageNo == 1) {
                totalCount = response.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<RestaurantData> rest = response.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> item.getFirstimage() != null && !item.getFirstimage().isEmpty())
                    .filter(item -> item.getAddr1() != null && !item.getAddr1().isEmpty())
                    .map(item -> new RestaurantData(
                            null,
                            item.getTitle(),
                            item.getZipcode(),
                            item.getAddr1(),
                            item.getAreacode(),
                            item.getContentid(),
                            item.getContenttypeid(),
                            item.getFirstimage(),
                            item.getMapx(),
                            item.getMapy(),
                            item.getSigungucode(),
                            item.getCat1(),
                            item.getCat2(),
                            item.getCat3(),
                            0
                    ))
                    .collect(Collectors.toList());

            restaurantSpots.addAll(rest);
            pageNo++;
        } while (pageNo <= totalPage);

        return restaurantSpots;
    }

    //문화시설
    @Override
    public List<CulturalData> fetchCulturalSpots(String areacode) {
        List<CulturalData> culturaldata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 100; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;

        do {
            String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=ETC&MobileApp=AppTest&ServiceKey=" + apiKey + "&listYN=Y&arrange=D&contentTypeId=14&areaCode=" + areacode + "&sigunguCode=&cat1=&cat2=&cat3=&_type=json";
            ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

            if (pageNo == 1) {
                totalCount = response.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<CulturalData> rest = response.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> item.getFirstimage() != null && !item.getFirstimage().isEmpty())
                    .filter(item -> item.getAddr1() != null && !item.getAddr1().isEmpty())
                    .map(item -> new CulturalData(
                            null,
                            item.getTitle(),
                            item.getZipcode(),
                            item.getAddr1(),
                            item.getAreacode(),
                            item.getContentid(),
                            item.getContenttypeid(),
                            item.getFirstimage(),
                            item.getMapx(),
                            item.getMapy(),
                            item.getSigungucode(),
                            item.getCat1(),
                            item.getCat2(),
                            item.getCat3(),
                            0
                    ))
                    .collect(Collectors.toList());

            culturaldata.addAll(rest);
            pageNo++;
        } while (pageNo <= totalPage);

        return culturaldata;
    }

    //축제행사
    @Override
    public List<FestivalData> fetchFestivalSpots(String areacode) {
        List<FestivalData> festivaldata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 100; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;

        do {
            String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=ETC&MobileApp=AppTest&ServiceKey=" + apiKey + "&listYN=Y&arrange=D&contentTypeId=15&areaCode=" + areacode + "&sigunguCode=&cat1=&cat2=&cat3=&_type=json";
            ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

            if (pageNo == 1) {
                totalCount = response.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<FestivalData> rest = response.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> item.getFirstimage() != null && !item.getFirstimage().isEmpty())
                    .filter(item -> item.getAddr1() != null && !item.getAddr1().isEmpty())
                    .map(item -> new FestivalData(
                            null,
                            item.getTitle(),
                            item.getZipcode(),
                            item.getAddr1(),
                            item.getAreacode(),
                            item.getContentid(),
                            item.getContenttypeid(),
                            item.getFirstimage(),
                            item.getMapx(),
                            item.getMapy(),
                            item.getSigungucode(),
                            item.getCat1(),
                            item.getCat2(),
                            item.getCat3(),
                            0
                    ))
                    .collect(Collectors.toList());

            festivaldata.addAll(rest);
            pageNo++;
        } while (pageNo <= totalPage);

        return festivaldata;
    }

    //레포츠
    @Override
    public List<SportsData> fetchSportsSpots(String areacode) {
        List<SportsData> sportsdata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 100; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;

        do {
            String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=ETC&MobileApp=AppTest&ServiceKey=" + apiKey + "&listYN=Y&arrange=D&contentTypeId=28&areaCode=" + areacode + "&sigunguCode=&cat1=&cat2=&cat3=&_type=json";
            ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

            if (pageNo == 1) {
                totalCount = response.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<SportsData> rest = response.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> item.getFirstimage() != null && !item.getFirstimage().isEmpty())
                    .filter(item -> item.getAddr1() != null && !item.getAddr1().isEmpty())
                    .map(item -> new SportsData(
                            null,
                            item.getTitle(),
                            item.getZipcode(),
                            item.getAddr1(),
                            item.getAreacode(),
                            item.getContentid(),
                            item.getContenttypeid(),
                            item.getFirstimage(),
                            item.getMapx(),
                            item.getMapy(),
                            item.getSigungucode(),
                            item.getCat1(),
                            item.getCat2(),
                            item.getCat3(),
                            0
                    ))
                    .collect(Collectors.toList());

            sportsdata.addAll(rest);
            pageNo++;
        } while (pageNo <= totalPage);

        return sportsdata;
    }

    
    //숙박
    @Override
    public List<LodgmentData> fetchLodgmentSpots(String areacode) {
        List<LodgmentData> lodgmentdata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 100; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;

        do {
            String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=ETC&MobileApp=AppTest&ServiceKey=" + apiKey + "&listYN=Y&arrange=D&contentTypeId=32&areaCode=" + areacode + "&sigunguCode=&cat1=&cat2=&cat3=&_type=json";
            ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

            if (pageNo == 1) {
                totalCount = response.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<LodgmentData> rest = response.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> item.getFirstimage() != null && !item.getFirstimage().isEmpty())
                    .filter(item -> item.getAddr1() != null && !item.getAddr1().isEmpty())
                    .map(item -> new LodgmentData(
                            null,
                            item.getTitle(),
                            item.getZipcode(),
                            item.getAddr1(),
                            item.getAreacode(),
                            item.getContentid(),
                            item.getContenttypeid(),
                            item.getFirstimage(),
                            item.getMapx(),
                            item.getMapy(),
                            item.getSigungucode(),
                            item.getCat1(),
                            item.getCat2(),
                            item.getCat3(),
                            0
                    ))
                    .collect(Collectors.toList());

            lodgmentdata.addAll(rest);
            pageNo++;
        } while (pageNo <= totalPage);

        return lodgmentdata;
    }
    
    
    //쇼핑
    @Override
    public List<ShoppingData> fetchShoppingSpots(String areacode) {
        List<ShoppingData> shoppingdata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 100; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;

        do {
            String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=ETC&MobileApp=AppTest&ServiceKey=" + apiKey + "&listYN=Y&arrange=D&contentTypeId=38&areaCode=" + areacode + "&sigunguCode=&cat1=&cat2=&cat3=&_type=json";
            ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

            if (pageNo == 1) {
                totalCount = response.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<ShoppingData> rest = response.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> item.getFirstimage() != null && !item.getFirstimage().isEmpty())
                    .filter(item -> item.getAddr1() != null && !item.getAddr1().isEmpty())
                    .map(item -> new ShoppingData(
                            null,
                            item.getTitle(),
                            item.getZipcode(),
                            item.getAddr1(),
                            item.getAreacode(),
                            item.getContentid(),
                            item.getContenttypeid(),
                            item.getFirstimage(),
                            item.getMapx(),
                            item.getMapy(),
                            item.getSigungucode(),
                            item.getCat1(),
                            item.getCat2(),
                            item.getCat3(),
                            0
                    ))
                    .collect(Collectors.toList());

            shoppingdata.addAll(rest);
            pageNo++;
        } while (pageNo <= totalPage);

        return shoppingdata;
    }

}