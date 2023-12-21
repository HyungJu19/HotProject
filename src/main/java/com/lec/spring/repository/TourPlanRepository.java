package com.lec.spring.repository;

import com.lec.spring.domain.TouristData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourPlanRepository {


    List<TouristData> findByConKey(@Param("areacode")String areacode,@Param("contenttypeid") String contenttypeid,@Param("keyword")String keyword,@Param("orderBy") String orderBy)
                                   ;


    List<TouristData> findByAreaCon(@Param("areacode") String areacode,@Param("contenttypeid") String contenttypeid,@Param("orderBy") String orderBy);

    List<TouristData> findAreaList(@Param("areacode") String areacode);




    }