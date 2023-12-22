package com.lec.spring.service;

import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TourPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourPlanServiceImpl implements TourPlanService{

    @Autowired
    private TourPlanRepository tourPlanRepository;
    @Override
    public List<TouristData> getAreaConKey(String areacode, String contenttypeid,String keyword, String orderBy ) {
        return tourPlanRepository.findByConKey(areacode,contenttypeid,keyword,orderBy);


    }
    @Override
    public List<TouristData> getAreaCon(String areacode, String contenttypeid, String orderBy) {
        return tourPlanRepository.findByAreaCon(areacode,contenttypeid,orderBy);
    }

    @Override
    public List<TouristData> getAreaList(String areacode) {
        return tourPlanRepository.findAreaList(areacode);
    }

    @Override
    public String getAreaXy(String areacode) {
        return switch (areacode){
            case "1" -> "37.5665,126.9780,11";
            case "2" -> "37.4563,126.7052,11";
            case "31" -> "37.4138,127.5183,11";
            case "32" -> "37.8228,128.1555,11";
            case "33" -> "36.6359,127.4913,11";
            case "34" -> "36.5184,126.8000,11";
            case "3" -> "36.3504,127.3845,11";
            case "8" -> "36.4801,127.2890,11";
            case "35" -> "36.4919,128.8889,11";
            case "36" -> "35.4606,128.2132,11";
            case "4"  -> "35.8714,128.6014,11";
            case "37" -> "35.7175,127.1530,11";
            case "38" -> "34.8679,126.9910,11";
            case "5" -> "35.1595,126.8526,11";
            case "7" -> "35.5384,129.3114,11";
            case "6" -> "35.1796,129.0756,11";
            case "39" -> "33.4890,126.4983,11";
            default -> null;
        };
    }




}
