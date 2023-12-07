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

    List<CampingData> getCampingImages();


    //투어
    List<TouristData> fetchTouristSpots(String areacode) throws UnsupportedEncodingException;



    List<TouristData> touristDataList(String areaCode,String contentTypeId,int limit,int offset);
}
