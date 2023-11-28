package com.lec.spring.service;

import com.lec.spring.domain.*;

import java.util.List;

public interface TouristService {

    //관광지
    List<TouristData> fetchTouristSpots(String areacode);

    //음식점
    List<RestaurantData> fetchRestaurantSpots(String areacode);

    //문화시설
    List<CulturalData> fetchCulturalSpots(String areacode);

    //축제행사
    List<FestivalData> fetchFestivalSpots(String areacode);

    //레포츠
    List<SportsData> fetchSportsSpots(String areacode);

    //숙박
    List<LodgmentData> fetchLodgmentSpots(String areacode);

    //쇼핑
    List<ShoppingData> fetchShoppingSpots(String areacode);
}
