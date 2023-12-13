/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.DTO.CampingResponse;
import com.lec.spring.domain.DTO.TouristApiResponse;
import com.lec.spring.domain.DTO.TouristDetailResponse;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristServiceImpl implements TouristService {


    private RestTemplate restTemplate;
    private TouristRepository touristRepository;

    @Value("${custom.api.key}")
    private String tourApiKey;

    @Value("${camping.api.key}")
    private String campingApiKey;

    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;


    @Autowired
    public TouristServiceImpl(SqlSession sqlSession) {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(20000); // 연결 타임아웃 10초
        clientHttpRequestFactory.setReadTimeout(20000); // 읽기 타임아웃 10초
        restTemplate = new RestTemplate();
        touristRepository = sqlSession.getMapper(TouristRepository.class);
    }


    //관광지
    @Override
    public List<TouristData> touristSpots(String areacode) {
        List<TouristData> allSpots = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 500; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;
        String baseUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        do {
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AppTest")
                    .queryParam("ServiceKey", tourApiKey)
                    .queryParam("listYN", "Y")
                    .queryParam("arrange", "D")
                    .queryParam("contentTypeId", 12)
                    .queryParam("areaCode", areacode)
                    .queryParam("sigunguCode", "")
                    .queryParam("cat1", "")
                    .queryParam("cat2", "")
                    .queryParam("cat3", "")
                    .queryParam("_type", "json")
                    .build(true)
                    .toUri();


            ResponseEntity<TouristApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, TouristApiResponse.class);
            TouristApiResponse touristApiResponse = response.getBody();
            System.out.println(response);
            if (pageNo == 1) {
                totalCount = touristApiResponse.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<TouristData> spots = touristApiResponse.getResponse().getBody().getItems().getItem().stream()
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
                            item.getCat3()
                    ))
                    .collect(Collectors.toList());

            allSpots.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return allSpots;
    }

    @Override
    public List<TouristData> touristDataList(String area, String areaCode, String contentTypeId, int limit, int offset) {
        return touristRepository.touristFindAll(area, areaCode, contentTypeId, limit, offset);

    }



    @Override
    public TouristData getTourById(String contentid, String contenttypeid) {

        return  touristRepository.findBytourdata(contentid,contenttypeid);
    }

    @Override
    public TouristDetailResponse getTourDetailById(String contentid, String contenttypeid) {
        String baseUrl = "https://apis.data.go.kr/B551011/KorService1/detailIntro1";
//        http://apis.data.go.kr/B551011/KorService1/detailIntro1?ServiceKey=인증키&contentTypeId=32&contentId=2465071&MobileOS=ETC&MobileApp=AppTest
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("ServiceKey", tourApiKey)
                .queryParam("contentTypeId", contenttypeid)
                .queryParam("contentId", contentid)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "AppTest")
                .queryParam("_type", "json")
                .build(true)
                .toUri();

        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TouristDetailResponse> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, null, TouristDetailResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            TouristDetailResponse response = responseEntity.getBody();
            System.out.println(response);
            return response;
        }
        return null;
    }
    @Override
    public List<CampingData> getRandomCampingSpotsByInduty(String induty) {

        // 데이터베이스에서 해당 induty에 해당하는 캠핑장 목록을 가져오는 예시
        List<CampingData> campingSpots = touristRepository.getCampingSpotsByInduty(induty);

        // 랜덤으로 셔플하여 4개만 선택
        Collections.shuffle(campingSpots);
        return campingSpots.stream().limit(4).collect(Collectors.toList());
    }

    @Override
    public List<CampingData> getRandomCampingSpotsBylctCl(String lctCl) {
    // 데이터베이스에서 해당 induty에 해당하는 캠핑장 목록을 가져오는 예시
        List<CampingData> campingSpots = touristRepository.getCampingSpotsBylctCl(lctCl);

        // 랜덤으로 셔플하여 4개만 선택
        Collections.shuffle(campingSpots);
        return campingSpots.stream().limit(4).collect(Collectors.toList());
    }

    @Override
    public CampingData getCampingById(String contentid) {
        return touristRepository.findBycampingdata(contentid);
    }

//    캠핑

    @Override
    public List<CampingData> fetchCampingSpots() {
        List<CampingData> campingSpots = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 500;    // 페이지당 행 수 설정
        String MobileOS = "ETC";
        String MobileApp = "testApp";
        int totalCount = 0;
        int totalPage = 0;
        String baseUrl = "https://apis.data.go.kr/B551011/GoCamping/basedList?";
        do {
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("MobileOS", MobileOS)
                    .queryParam("MobileApp", MobileApp)
                    .queryParam("ServiceKey", campingApiKey)
                    .queryParam("_type", "json")
                    .build(true)
                    .toUri();

            System.out.println(uri);
            ResponseEntity<CampingResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, CampingResponse.class);
            System.out.println(uri);
            CampingResponse campingResponse = response.getBody();
            if (pageNo == 1) {
                totalCount = campingResponse.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount/numOfRows);
            }

            List<CampingData> spots = campingResponse.getResponse().getBody().getItems().getItem().stream()
                    .filter(item -> item.getLctCl() != null && !item.getLctCl().isEmpty())  // 입지구분 필터
                    .filter(item -> item.getThemaEnvrnCl() != null && !item.getThemaEnvrnCl().isEmpty())    // 테마환경 필터
                    .map(item -> new CampingData(
                            null,
                            item.getFacltNm(),
                            item.getIntro(),
                            item.getInduty(),
                            item.getLctCl(),
                            item.getDoNm(),
                            item.getSigunguNm(),
                            item.getAddr1(),
                            item.getMapX(),
                            item.getMapY(),
                            item.getTel(),
                            item.getOperPdCl(),
                            item.getOperDeCl(),
                            item.getTourEraCl(),
                            item.getFirstImageUrl(),
                            item.getPosblFcltyCl(),
                            item.getThemaEnvrnCl(),
                            item.getAnimalCmgCl(),
                            item.getContentId()
                    ))
                    .collect(Collectors.toList());
            System.out.println(spots);
            campingSpots.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return campingSpots;
    }

    @Override
    public List<CampingData> campingDataList(String doNm,String areaCode,int limit,int offset) {
        return touristRepository.searchCampingFindAll(doNm,limit,offset);

    }

    @Override
    public int getTotalDataCount(String areaCode, String contentTypeId) {
        return touristRepository.getTotalDataCount(areaCode, contentTypeId);
    }




//    @Override
//    public List<CampingData> getCampingImages() {
//        return null;
//    }

    //음식점
    @Override
    public List<TouristData> fetchRestaurantSpots(String areacode) {
        List<TouristData> restaurantSpots = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 500; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;
        String baseUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        do {
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AppTest")
                    .queryParam("ServiceKey", tourApiKey)
                    .queryParam("listYN", "Y")
                    .queryParam("arrange", "D")
                    .queryParam("contentTypeId", 39)
                    .queryParam("areaCode", areacode)
                    .queryParam("sigunguCode", "")
                    .queryParam("cat1", "")
                    .queryParam("cat2", "")
                    .queryParam("cat3", "")
                    .queryParam("_type", "json")
                    .build(true)
                    .toUri();


            System.out.println(uri);
            ResponseEntity<TouristApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, TouristApiResponse.class);
            System.out.println(uri);
            TouristApiResponse touristApiResponse = response.getBody();
            if (pageNo == 1) {
                totalCount = touristApiResponse.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<TouristData> spots = touristApiResponse.getResponse().getBody().getItems().getItem().stream()
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
                            item.getCat3()
                    ))
                    .collect(Collectors.toList());

            restaurantSpots.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return restaurantSpots;
    }

    //문화시설
    @Override
    public List<TouristData> fetchCulturalSpots(String areacode) {
        List<TouristData> culturaldata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 500; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;
        String baseUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        do {
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AppTest")
                    .queryParam("ServiceKey", tourApiKey)
                    .queryParam("listYN", "Y")
                    .queryParam("arrange", "D")
                    .queryParam("contentTypeId", 14)
                    .queryParam("areaCode", areacode)
                    .queryParam("sigunguCode", "")
                    .queryParam("cat1", "")
                    .queryParam("cat2", "")
                    .queryParam("cat3", "")
                    .queryParam("_type", "json")
                    .build(true)
                    .toUri();


            ResponseEntity<TouristApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, TouristApiResponse.class);
            TouristApiResponse touristApiResponse = response.getBody();
            System.out.println(response);
            if (pageNo == 1) {
                totalCount = touristApiResponse.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<TouristData> spots = touristApiResponse.getResponse().getBody().getItems().getItem().stream()
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
                            item.getCat3()
                    ))
                    .collect(Collectors.toList());

            culturaldata.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return culturaldata;
    }


    //축제행사
    @Override
    public List<TouristData> fetchFestivalSpots(String areacode) {
        List<TouristData> festivaldata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 500; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;
        String baseUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        do {
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AppTest")
                    .queryParam("ServiceKey", tourApiKey)
                    .queryParam("listYN", "Y")
                    .queryParam("arrange", "D")
                    .queryParam("contentTypeId", 15)
                    .queryParam("areaCode", areacode)
                    .queryParam("sigunguCode", "")
                    .queryParam("cat1", "")
                    .queryParam("cat2", "")
                    .queryParam("cat3", "")
                    .queryParam("_type", "json")
                    .build(true)
                    .toUri();


            ResponseEntity<TouristApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, TouristApiResponse.class);
            TouristApiResponse touristApiResponse = response.getBody();
            System.out.println(response);
            if (pageNo == 1) {
                totalCount = touristApiResponse.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<TouristData> spots = touristApiResponse.getResponse().getBody().getItems().getItem().stream()
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
                            item.getCat3()
                    ))
                    .collect(Collectors.toList());

            festivaldata.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return festivaldata;
    }



    //레포츠
    @Override
    public List<TouristData> fetchSportsSpots(String areacode) {
        List<TouristData> sportsdata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 500; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;
        String baseUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        do {
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AppTest")
                    .queryParam("ServiceKey", tourApiKey)
                    .queryParam("listYN", "Y")
                    .queryParam("arrange", "D")
                    .queryParam("contentTypeId", 28)
                    .queryParam("areaCode", areacode)
                    .queryParam("sigunguCode", "")
                    .queryParam("cat1", "")
                    .queryParam("cat2", "")
                    .queryParam("cat3", "")
                    .queryParam("_type", "json")
                    .build(true)
                    .toUri();


            ResponseEntity<TouristApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, TouristApiResponse.class);
            TouristApiResponse touristApiResponse = response.getBody();
            System.out.println(response);
            if (pageNo == 1) {
                totalCount = touristApiResponse.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<TouristData> spots = touristApiResponse.getResponse().getBody().getItems().getItem().stream()
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
                            item.getCat3()
                    ))
                    .collect(Collectors.toList());

            sportsdata.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return sportsdata;
    }


    //숙박
    @Override
    public List<TouristData> fetchLodgmentSpots(String areacode) {
        List<TouristData> lodgmentdata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 500; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;
        String baseUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        do {
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AppTest")
                    .queryParam("ServiceKey", tourApiKey)
                    .queryParam("listYN", "Y")
                    .queryParam("arrange", "D")
                    .queryParam("contentTypeId", 32)
                    .queryParam("areaCode", areacode)
                    .queryParam("sigunguCode", "")
                    .queryParam("cat1", "")
                    .queryParam("cat2", "")
                    .queryParam("cat3", "")
                    .queryParam("_type", "json")
                    .build(true)
                    .toUri();


            ResponseEntity<TouristApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, TouristApiResponse.class);
            TouristApiResponse touristApiResponse = response.getBody();
            System.out.println(response);
            if (pageNo == 1) {
                totalCount = touristApiResponse.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<TouristData> spots = touristApiResponse.getResponse().getBody().getItems().getItem().stream()
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
                            item.getCat3()
                    ))
                    .collect(Collectors.toList());

            lodgmentdata.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return lodgmentdata;
    }

    //쇼핑
    @Override
    public List<TouristData> fetchShoppingSpots(String areacode) {
        List<TouristData> shoppingdata = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 500; // 페이지당 행 수 설정
        int totalCount = 0;
        int totalPage = 0;
        String baseUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        do {
            URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo)
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AppTest")
                    .queryParam("ServiceKey", tourApiKey)
                    .queryParam("listYN", "Y")
                    .queryParam("arrange", "D")
                    .queryParam("contentTypeId", 38)
                    .queryParam("areaCode", areacode)
                    .queryParam("sigunguCode", "")
                    .queryParam("cat1", "")
                    .queryParam("cat2", "")
                    .queryParam("cat3", "")
                    .queryParam("_type", "json")
                    .build(true)
                    .toUri();


            ResponseEntity<TouristApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, TouristApiResponse.class);
            TouristApiResponse touristApiResponse = response.getBody();
            System.out.println(response);
            if (pageNo == 1) {
                totalCount = touristApiResponse.getResponse().getBody().getTotalCount();
                totalPage = (int) Math.ceil((double) totalCount / numOfRows);
            }

            List<TouristData> spots = touristApiResponse.getResponse().getBody().getItems().getItem().stream()
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
                            item.getCat3()
                    ))
                    .collect(Collectors.toList());

            shoppingdata.addAll(spots);
            pageNo++;
        } while (pageNo <= totalPage);

        return shoppingdata;
    }



    public int getTotalAreacodeCount(String areacode ,String contenttypeid ){
        return touristRepository.getTourAreacodeTotalCount(areacode,contenttypeid);
    }


    public int getConpingAreaTotalCount(String doNm){
        return touristRepository.getConpingAreaTotalCount(doNm);
    }
}