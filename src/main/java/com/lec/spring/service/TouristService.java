/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.*;

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


    List<TouristData> touristDataList(String areaCode,String contentTypeId,int limit,int offset);
}
