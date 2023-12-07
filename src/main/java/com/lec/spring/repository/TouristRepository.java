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
            @Param("areacode") String areacode,
            @Param("contenttypeid") String contenttypeid,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    int countCampingData();

    public void saveOrUpdateCamping (CampingData campingData);

    List<CampingData> campingFindAll(@Param("limit") int limit, @Param("offset") int offset);

}

