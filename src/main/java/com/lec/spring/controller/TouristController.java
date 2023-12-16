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

    private static final String KARAVAN_CATEGORY = "karavan";
    private static final String GLAMPING_CATEGORY = "glamping";
    private static final String CAMPING_CATEGORY = "camping";
    private static final String AUTOCAMPING_CATEGORY = "autoCamping";
    private static final String RIVER_THEME = "river";
    private static final String LAKE_THEME = "lake";
    private static final String VALLEY_THEME = "valley";
    private static final String DOWNTOWN_THEME = "downtown";
    private static final String MOUNTAIN_THEME = "mountain";
    private static final String FOREST_THEME = "forest";
    private static final String ISLAND_THEME = "island";
    private static final String BEACH_THEME = "beach";

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
    public List<CampingData> getRandomCampingSpots(@RequestParam String category) {
        logger.info("Controller method called!");

        return getRandomCampingSpotsByCategory(category);
    }

    private List<CampingData> getRandomCampingSpotsByCategory(String category) {
        String induty = getIndutyByCategory(category);
        List<CampingData> campingData = touristService.getRandomCampingSpotsByInduty(induty);

        return campingData.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.shuffle(list);
                            return list.stream().limit(4).collect(Collectors.toList());
                        }));
    }


    private String getIndutyByCategory(String category) {
        switch (category) {
            case KARAVAN_CATEGORY:
                return "카라반";
            case GLAMPING_CATEGORY:
                return "글램핑";
            case CAMPING_CATEGORY:
                return "일반야영장";
            case AUTOCAMPING_CATEGORY:
                return "자동차야영장";
            default:
                return "";
        }
    }


    @GetMapping("/getLctClCampingSpots")
    @ResponseBody
    public List<CampingData> getLctClCampingSpots(@RequestParam String theme) {
        logger.info("Controller method called!");

        return getLctClCampingSpotsByTheme(theme);
    }

    private List<CampingData> getLctClCampingSpotsByTheme(String theme) {
        String lctCl = getLctClByTheme(theme);
        List<CampingData> campingData = touristService.getRandomCampingSpotsBylctCl(lctCl);

        return campingData.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.shuffle(list);
                            return list.stream().limit(4).collect(Collectors.toList());
                        }));
    }

    private String getLctClByTheme(String theme) {
        switch (theme) {
            case RIVER_THEME:
                return "강";
            case LAKE_THEME:
                return "호수";
            case VALLEY_THEME:
                return "계곡";
            case DOWNTOWN_THEME:
                return "도심";
            case MOUNTAIN_THEME:
                return "산";
            case FOREST_THEME:
                return "숲";
            case ISLAND_THEME:
                return "섬";
            case BEACH_THEME:
                return "해변";
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