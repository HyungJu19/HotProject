package com.lec.spring.repository;


import com.lec.spring.domain.CampingData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CampingRepository {
    int countCampingData();

    public void saveOrUpdateCamping (CampingData campingData);

    List<CampingData> campingFindAll(@Param("limit") int limit, @Param("offset") int offset);

}
