package com.lec.spring.controller;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.TouristData;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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
        return "redirect:/touristSpots";
    }



    @GetMapping("/theme/camping/main")
    public String main(Model model, @RequestParam(defaultValue = "1") int page) {
        int limit = 4;
        int offset = (page - 1) * limit;

        List<CampingData> campingSpots = touristRepository.campingFindAll(limit, offset);
        model.addAttribute("campingSpots", campingSpots);

        return "theme/camping/main";
    }


    @GetMapping("/getRandomCampingSpots")
    @ResponseBody
    public List<CampingData> getRandomCampingSpots(Model model) {
        System.out.println("Controller method called!"); // 또는 로깅 프레임워크를 사용하세요
        List<CampingData> karavanCampingSpots = getRandomCampingSpotsByCategory("karavan");
        List<CampingData> glampingCampingSpots = getRandomCampingSpotsByCategory("glamping");
        List<CampingData> campingCampingSpots = getRandomCampingSpotsByCategory("camping");
        List<CampingData> autoCampingCampingSpots = getRandomCampingSpotsByCategory("autoCamping");

        List<CampingData> allCampingSpots = new ArrayList<>();
        allCampingSpots.addAll(karavanCampingSpots);
        allCampingSpots.addAll(glampingCampingSpots);
        allCampingSpots.addAll(campingCampingSpots);
        allCampingSpots.addAll(autoCampingCampingSpots);

        return allCampingSpots;
    }

    // 새로운 랜덤 캠핑장 목록 가져오는 메소드
    private List<CampingData> getRandomCampingSpotsByCategory(String category) {
        // induty 값 가져오기
        String induty = getIndutyByCategory(category);

        // 전체 캠핑장 목록 가져오기
        List<CampingData> campingData = touristService.getRandomCampingSpotsByInduty(induty);

        // 랜덤으로 셔플하여 4개만 선택
        Collections.shuffle(campingData);
        return campingData.stream().limit(4).collect(Collectors.toList());
    }

    // 간단한 category와 induty 매핑 메소드 예시
    private String getIndutyByCategory(String category) {
        // 실제로는 데이터베이스나 다른 매핑 방식을 사용할 것
        switch (category) {
            case "karavan":
                return "카라반";
            case "glamping":
                return "글램핑";
            case "camping":
                return "일반야영장";
            case "autoCamping":
                return "자동차야영장";
            default:
                return "";
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
        return "redirect:/touristSpots";
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
        return "redirect:/touristSpots";
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
        return "redirect:/touristSpots";
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
        return "redirect:/touristSpots";
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
        return "redirect:/touristSpots";
    }
    @Async
    @GetMapping
    public void updateTShoppingSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchShoppingSpots(areacode);
        for (TouristData spot : spots) {
            touristRepository.saveOrUpdateShopping(spot);
        }
    }
}