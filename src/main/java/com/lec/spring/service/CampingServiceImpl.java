package com.lec.spring.service;


import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.CampingResponse;
import com.lec.spring.repository.CampingRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampingServiceImpl implements CampingService {

    private RestTemplate restTemplate;

    private CampingRepository campingRepository;

    @Value("${camping.api.key}")
    private String apiKey;

    @Autowired
    public CampingServiceImpl(SqlSession sqlSession){
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(20000);  // 연결 타임아웃 10초
        clientHttpRequestFactory.setReadTimeout(20000); // 읽기 타임아웃 10초
        restTemplate = new RestTemplate();
        campingRepository = sqlSession.getMapper(CampingRepository.class);
    }


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
                    .queryParam("ServiceKey", apiKey)
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
    public List<CampingData> getCampingImages() {
        return null;
    }


}
