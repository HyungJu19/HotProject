package com.lec.spring.controller;

import com.lec.spring.domain.*;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;


//민호
@Controller
@RequestMapping("/")
public class TouristController {

    @Autowired
    private TouristService touristService;

    @Autowired
    private TouristRepository touristRepository;
    private static final Logger logger = LoggerFactory.getLogger(TouristController.class);

    @GetMapping("/touristSpots")
    public String viewTouristSpots( Model model) {



        return "touristSpots"; // 관광지 정보를 담은 뷰 반환
    }




    //관광지

    @GetMapping("/updateAllTouristSpots")
    public String updateAllTouristSpots() throws UnsupportedEncodingException {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            try {
                updateTouristSpotsForArea(areacode);
            } catch (Exception e) {
                // 로깅 강화: 실패한 경우에 대한 로그 기록
                logger.error("Error updating tourist spots for area code " + areacode, e);
            }
        }
        return "redirect:/touristSpots";
    }
    @Async
    @Transactional
    public void updateTouristSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchTouristSpots(areacode);
        for (TouristData spot : spots) {
            touristRepository.saveOrUpdateTourist(spot);
        }
    }

    //음식점

    @GetMapping("/updateAllRestaurantSpots")
    public String updateAllRestaurantSpots() throws UnsupportedEncodingException {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            try {
                updateTRestaurantSpotsForArea(areacode);
            } catch (Exception e) {
                // 로깅 강화: 실패한 경우에 대한 로그 기록
                logger.error("Error updating tourist spots for area code " + areacode, e);
            }
        }
        return "redirect:/touristSpots";
    }

    @Async
    @Transactional
    public void updateTRestaurantSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<RestaurantData> spots = touristService.fetchRestaurantSpots(areacode);
        for (RestaurantData spot : spots) {
            touristRepository.saveOrUpdateRestaurant(spot);
        }
    }

    //문화시설

    @GetMapping("/updateAllCulturalDataSpots")
    public String updateAllCulturalDataSpots() throws UnsupportedEncodingException {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            try {
                updateTCulturalpotsForArea(areacode);
            } catch (Exception e) {
                // 로깅 강화: 실패한 경우에 대한 로그 기록
                logger.error("Error updating tourist spots for area code " + areacode, e);
            }
        }
        return "redirect:/touristSpots";
    }
    @Async
    @Transactional
    public void updateTCulturalpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<CulturalData> spots = touristService.fetchCulturalSpots(areacode);
        for (CulturalData spot : spots) {
            touristRepository.saveOrUpdateCultural(spot);
        }
    }

    //축체행사

    @GetMapping("/updateAllFestivalDataSpots")
    public String updateAllFestivalDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            try {
                updateTRFestivalSpotsForArea(areacode);
            } catch (Exception e) {
                // 로깅 강화: 실패한 경우에 대한 로그 기록
                logger.error("Error updating tourist spots for area code " + areacode, e);
            }
        }
        return "redirect:/touristSpots";
    }
    @Async
    @Transactional
    public void updateTRFestivalSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<FestivalData> spots = touristService.fetchFestivalSpots(areacode);
        for (FestivalData spot : spots) {
            touristRepository.saveOrUpdateFestival(spot);
        }
    }


    //레포츠

    @GetMapping("/updateAllSportsDataSpots")
    public String updateAllSportsDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            try {
                updateTSportsSpotsForArea(areacode);
            } catch (Exception e) {
                // 로깅 강화: 실패한 경우에 대한 로그 기록
                logger.error("Error updating tourist spots for area code " + areacode, e);
            }
        }
        return "redirect:/touristSpots";
    }
    @Async
    @Transactional
    public void updateTSportsSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<SportsData> spots = touristService.fetchSportsSpots(areacode);
        for (SportsData spot : spots) {
            touristRepository.saveOrUpdateSports(spot);
        }
    }


    //숙박

    @GetMapping("/updateAllLodgmentDataSpots")
    public String updateAllLodgmentDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            try {
                updateTLodgmentDataSpotsForArea(areacode);
            } catch (Exception e) {
                // 로깅 강화: 실패한 경우에 대한 로그 기록
                logger.error("Error updating tourist spots for area code " + areacode, e);
            }
        }
        return "redirect:/touristSpots";
    }
    @Async
    @Transactional
    public void updateTLodgmentDataSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<LodgmentData> spots = touristService.fetchLodgmentSpots(areacode);
        for (LodgmentData spot : spots) {
            touristRepository.saveOrUpdateLodgment(spot);
        }
    }


    //쇼핑

    @GetMapping("/updateAllShoppingDataSpots")
    public String updateAllShoppingDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            try {
                updateTShoppingSpotsForArea(areacode);
            } catch (Exception e) {
                // 로깅 강화: 실패한 경우에 대한 로그 기록
                logger.error("Error updating tourist spots for area code " + areacode, e);
            }
        }
        return "redirect:/touristSpots";
    }
    @Async
    @GetMapping
    public void updateTShoppingSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<ShoppingData> spots = touristService.fetchShoppingSpots(areacode);
        for (ShoppingData spot : spots) {
            touristRepository.saveOrUpdateShopping(spot);
        }
    }

}