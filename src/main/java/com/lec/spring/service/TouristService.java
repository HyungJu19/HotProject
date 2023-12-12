/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.TouristData;
import com.lec.spring.domain.TouristDetail;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface TouristService {
    //캠핑
    List<CampingData> fetchCampingSpots() throws UnsupportedEncodingException;




    //관광지
    List<TouristData> touristSpots(String areacode) throws UnsupportedEncodingException;
    //음식점
    List<TouristData> fetchRestaurantSpots(String areacode) throws UnsupportedEncodingException;

    //문화시설
    List<TouristData> fetchCulturalSpots(String areacode) throws UnsupportedEncodingException;

    //축제행사
    List<TouristData> fetchFestivalSpots(String areacode);

    //레포츠
    List<TouristData> fetchSportsSpots(String areacode);

    //숙박
    List<TouristData> fetchLodgmentSpots(String areacode);

    //쇼핑
    List<TouristData> fetchShoppingSpots(String areacode);


    int getTotalAreacodeCount(String areacode, String contenttypeid);
    int getConpingAreaTotalCount(String doNm);
    List<TouristData> touristDataList(String area,String areaCode,String contentTypeId,int limit,int offset);


    List<CampingData> campingDataList(String doNm,String areaCode,int limit,int offset);




    TouristData getTourById(String contentid,String contenttypeid);

    TouristDetail getTourDetailById(String contentid ,String contenttypeid);

    // 전체 아이템 수 조회
    int getTotalDataCount(String areaCode, String contentTypeId);

}
