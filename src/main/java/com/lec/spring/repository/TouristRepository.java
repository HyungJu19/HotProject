/**
 * #민호
 */



package com.lec.spring.repository;

import com.lec.spring.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

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

    List<TouristData> foodFindAll(
            @Param("areaCode") String areaCode,
            @Param("sigungucode") String sigungucode,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    @Transactional
    int incViewCnt(String contentId);
    @Transactional
    int incViewCamCnt(String contentId);
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

     List<TouristData> findBytourContentId(String contentid);

     TouristData findBytourdata(String contentid,String contenttypeid );
    CampingData findBycompingdata( String doNm,String campingContentid);


    List<CampingData> campingFindAll(@Param("induty") String induty, @Param("lctCl") String lctCl);

    List<CampingData> campingRecommend();



    int getTotalDataCount(String areaCode, String contentTypeId);

//    좋아요
    List<TourLikeList> findByLike(Long uid);
    List<CampingLikeList> findBycamLike(Long uid);

    int findLike(@Param("uid") Long uid, @Param("id")Long id);
    int findCamLike(@Param("uid") Long uid,@Param("id") Long id);

    int getLikeCount(@Param("id") Long id);

    int getCamLikeCount(@Param("id") Long id);
    int totalView(String contentId);



    List<CampingData> campingSearch(String keyword, int limit, int offset);

    List<TouristData> tourSearch(String keyword, int limit, int offset);

    int CampingSearchDataCount(String keyword);

    int TourSearchDataCount(String keyword);
    int totalCamView(String contentid);


    //좋아
    List<TouristData> myTourCntAll(Long id);

    List<Post> myPostList(Long uid);

}

