package com.lec.spring.service;

import com.lec.spring.domain.TouristData;

import java.util.List;

public interface TourPlanService {

//    areacode + contenttypeid + keyword
    List<TouristData> getAreaConKey(String areacode, String contenttypeid,String keyword, String orderBy);


//    areacode + contenttypeid만
    List<TouristData> getAreaCon(String areacode, String contenttypeid,String orderBy);


//    areacode 만
    List<TouristData> getAreaList(String areacode );

//    좌표값
    String getAreaXy(String areacode);


}
