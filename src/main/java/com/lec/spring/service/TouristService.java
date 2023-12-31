/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.DTO.LocalFoodieResponse;
import com.lec.spring.domain.DTO.TouristDetailResponse;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.TouristData;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
    List<TouristData> touristDataList1(String area,String areaCode,String contentTypeId,String orderby ,int limit,int offset);

    //
    List<TouristData> localfoodie(String areacode, String sigungucode, int limit, int offset);




    List<CampingData> campingDataList(String doNm,String areaCode,String orderby,int limit,int offset);


    int getLike(Long uid, Long id);
    int getcamLike(Long uid, Long id);
    TouristData getTourById(String contentid,String contenttypeid);
    CampingData getCompingById(String campingContentid, String doNm);
    TouristDetailResponse getTourDetailById(String contentid , String contenttypeid);


    // 전체 아이템 수 조회
    int getTotalDataCount(String areaCode, String contentTypeId);




    List<CampingData> campingList(String induty, String lctCl);

    List<CampingData> recommentList();


    List<CampingData> campingSearchData(String keyword, int climit, int coffset);

    List<TouristData> tourSearchData(String keyword, int tlimit, int toffset);

    int getTotalCampingSearchDataCount(String keyword);

    int getTotalTourSearchDataCount(String keyword);
    // 좋아요 다 부르기
    List<TouristData> myTourCntAll (Long uid);

    List<Post> myPostList (Long uid);

    List<Post> postList (String category, String visibility);

    LocalFoodieResponse getFindByLocal(String mapX, String mapY);

    List<TouristData> search(String category,String keyword);

    List<CampingData> searchCamping(String category,String keyword);

    List<TouristData> tourLike (String areacode,String contenttypeid,String count, int page, int size);

    List<Map<String, Object>> getcitiCount();
}
