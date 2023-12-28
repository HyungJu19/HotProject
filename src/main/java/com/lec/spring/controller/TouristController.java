package com.lec.spring.controller;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;


//민호
@Controller
@RequestMapping("/update")
public class TouristController {

    @Autowired
    private TouristService touristService;

    @Autowired
    private TouristRepository touristRepository;
    private static final Logger logger = LoggerFactory.getLogger(TouristController.class);



    @GetMapping("/touristSpots")
    public String viewTouristSpots( Model model) {



        return "update/touristSpots"; // 관광지 정보를 담은 뷰 반환
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
        return "redirect:/update/touristSpots";
    }
    @Async
    @Transactional
    public void updateTouristSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.touristSpots(areacode);
        for (TouristData spot : spots) {
            touristRepository.saveOrUpdateTourist(spot);
        }
    }




    //캠핑
    @GetMapping("/updateAllCampingSpots")
    public String updateAllCampingSpots() {
        try {
            // 캠핑 서비스에서 모든 캠핑장 데이터를 가져와 데이터베이스에 저장
            List<CampingData> campingSpots = touristService.fetchCampingSpots();
            for (CampingData spot : campingSpots) {
                touristRepository.saveOrUpdateCamping(spot);
            }
        } catch (Exception e) {
            // 로깅 강화: 실패한 경우에 대한 로그 기록
            logger.error("Error updating all camping spots", e);
        }

        // 모든 캠핑장 업데이트가 완료되면 캠핑장 페이지로 리다이렉트
        return "redirect:/update/touristSpots";
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
        return "redirect:/update/touristSpots";
    }

    @Async
    @Transactional
    public void updateTRestaurantSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchRestaurantSpots(areacode);
        for (TouristData spot : spots) {
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
        return "redirect:/update/touristSpots";
    }
    @Async
    @Transactional
    public void updateTCulturalpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchCulturalSpots(areacode);
        for (TouristData spot : spots) {
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
        return "redirect:/update/touristSpots";
    }
    @Async
    @Transactional
    public void updateTRFestivalSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchFestivalSpots(areacode);
        for (TouristData spot : spots) {
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
        return "redirect:/update/touristSpots";
    }
    @Async
    @Transactional
    public void updateTSportsSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchSportsSpots(areacode);
        for (TouristData spot : spots) {
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
        return "redirect:/update/touristSpots";
    }
    @Async
    @Transactional
    public void updateTLodgmentDataSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchLodgmentSpots(areacode);
        for (TouristData spot : spots) {
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
        return "redirect:/update/touristSpots";
    }
    @Async
    @GetMapping
    public void updateTShoppingSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchShoppingSpots(areacode);
        for (TouristData spot : spots) {
            touristRepository.saveOrUpdateShopping(spot);
        }
    }

    @GetMapping("addDestination/search/{category}/{keyword}")
    @ResponseBody
    public List<TouristData> search(@PathVariable String category,@PathVariable String keyword, Model model){
        List<TouristData> searchList = touristService.search(category,keyword);

        model.addAttribute("searchList",searchList);

        return searchList;
    }
    @GetMapping("camping/search/{category}/{keyword}")
    @ResponseBody
    public List<CampingData> searchCamping(@PathVariable String category, @PathVariable String keyword, Model model){
        List<CampingData> searchCampingList = touristService.searchCamping(category,keyword);

        model.addAttribute("searchList",searchCampingList);

        return searchCampingList;
    }
}