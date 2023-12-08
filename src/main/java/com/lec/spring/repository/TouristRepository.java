/**
 * #민호
 */



package com.lec.spring.repository;

import com.lec.spring.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TouristRepository  {

    //관광지
    public void saveOrUpdateTourist (TouristData touristSpot);


    List<TouristData> touristFindAll(
            @Param("area") String area,
            @Param("areacode") String areacode,
            @Param("contenttypeid") String contenttypeid,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    //캠핑
//    int countCampingData();

    public void saveOrUpdateCamping (CampingData campingData);

//    List<CampingData> campingFindAll(@Param("limit") int limit, @Param("offset") int offset);
    List<CampingData> searchCampingFindAll(
            @Param("doNm")String doNm,
            @Param("limit") int limit,
            @Param("offset") int offset
    );


    //음식점
    public void saveOrUpdateRestaurant (TouristData touristData);

    //문화시설
    public void saveOrUpdateCultural (TouristData touristData);

    //축제행사
    public void saveOrUpdateFestival (TouristData touristData);

    //레포츠
    public void saveOrUpdateSports (TouristData touristData);

    //숙박
    public void saveOrUpdateLodgment (TouristData touristData);

    //쇼핑
    public void saveOrUpdateShopping (TouristData touristData);

    public int getTourAreacodeTotalCount(String areacode ,String contenttypeid);

    public int getConpingAreaTotalCount(String doNm);
    List<CampingData> getCampingSpotsByInduty(@Param("induty") String induty);
}

