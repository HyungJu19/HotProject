/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.DTO.TouristDetailResponse;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.TouristData;
import org.springframework.transaction.annotation.Transactional;

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

//    List<TouristData> foodDataList(String areaCode, int limit, int offset);
    List<TouristData> localfoodie(String areaCode, String sigungucode, int limit, int offset);




    List<CampingData> campingDataList(String doNm,String areaCode,int limit,int offset);


    int getLike(Long uid, Long id);
    int getcamLike(Long uid, Long id);
    TouristData getTourById(String contentid,String contenttypeid);
    CampingData getCompingById(String campingContentid, String doNm);
    TouristDetailResponse getTourDetailById(String contentid , String contenttypeid);


    // 전체 아이템 수 조회
    int getTotalDataCount(String areaCode, String contentTypeId);




    List<CampingData> campingList(String induty, String lctCl);

    List<CampingData> recommentList();


    List<CampingData> campingSearchData(String keyword, int limit, int offset);

    List<TouristData> tourSearchData(String keyword, int limit, int offset);

    int getTotalCampingSearchDataCount(String keyword);

    int getTotalTourSearchDataCount(String keyword);
    // 좋아요 다 부르기
    List<TouristData> myTourCntAll (Long uid);

    List<Post> myPostList (Long uid);

    List<Post> postList (String category, String visibilityl);

}
