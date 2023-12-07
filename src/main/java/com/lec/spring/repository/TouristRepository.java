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
    int countTouristData();
    int countRestauranData();
    int countCulturalData();
    int countFestivalData();
    int countSporttData();
    int countLodgmentData();
    int countShoppingData();
    //관광지
    public void saveOrUpdateTourist (TouristData touristSpot);
    
    //음식점
    public void saveOrUpdateRestaurant (RestaurantData restaurantData);
    
    //문화시설
    public void saveOrUpdateCultural (CulturalData culturalData);
    
    //축제행사
    public void saveOrUpdateFestival (FestivalData festivalData);

    //레포츠
    public void saveOrUpdateSports (SportsData sportsData);

    //숙박
    public void saveOrUpdateLodgment (LodgmentData lodgmentData);

    //쇼핑
    public void saveOrUpdateShopping (ShoppingData shoppingData);
    List<TouristData> touristFindAll(@Param("areacode")String areacode, @Param("contenttypeid") String contenttypeid );

}

