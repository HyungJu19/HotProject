package com.lec.spring.controller;

import com.lec.spring.domain.*;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/touristSpots")
    public String viewTouristSpots( Model model) {



        return "touristSpots"; // 관광지 정보를 담은 뷰 반환
    }




    //관광지

    @PostMapping("/insertTouristSpots")
    public String insertTouristSpots(@RequestParam String areacode) {
        List<TouristData> spots = touristService.fetchTouristSpots(areacode);
        for (TouristData spot : spots) {
            touristRepository.saveOrUpdateTourist (spot); // 'firstimage'가 있는 관광지 정보만 데이터베이스에 저장
        }
        return "redirect:/touristSpots?areacode=" + areacode;
    }




    @PostMapping("/updateAllTouristSpots")
    public String updateAllTouristSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            List<TouristData> spots = touristService.fetchTouristSpots(areacode);
            for (TouristData spot : spots) {
                touristRepository.saveOrUpdateTourist(spot);
            }
        }
        return "redirect:/touristSpots";
    }


    //음식점
    @PostMapping("/insertRestaurantSpots")
    public String insertRestaurantSpots(@RequestParam String areacode) {
        List<RestaurantData> spots = touristService.fetchRestaurantSpots(areacode);
        for (RestaurantData spot : spots) {
            touristRepository.saveOrUpdateRestaurant (spot); // 'firstimage'가 있는 관광지 정보만 데이터베이스에 저장
        }
        return "redirect:/touristSpots?areacode=" + areacode;
    }
    @PostMapping("/updateAllRestaurantSpots")
    public String updateAllRestaurantSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            List<RestaurantData> spots = touristService.fetchRestaurantSpots(areacode);
            for (RestaurantData spot : spots) {
                touristRepository.saveOrUpdateRestaurant(spot);
            }
        }
        return "redirect:/touristSpots";
    }


    //문화시설
    @PostMapping("/insertCulturalDataSpots")
    public String insertCulturalDataSpots(@RequestParam String areacode) {
        List<CulturalData> spots = touristService.fetchCulturalSpots(areacode);
        for (CulturalData spot : spots) {
            touristRepository.saveOrUpdateCultural (spot); // 'firstimage'가 있는 관광지 정보만 데이터베이스에 저장
        }
        return "redirect:/touristSpots?areacode=" + areacode;
    }
    @PostMapping("/updateAllCulturalDataSpots")
    public String updateAllCulturalDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            List<CulturalData> spots = touristService.fetchCulturalSpots(areacode);
            for (CulturalData spot : spots) {
                touristRepository.saveOrUpdateCultural(spot);
            }
        }
        return "redirect:/touristSpots";
    }

    //축체행사
    @PostMapping("/insertFestivalDataSpots")
    public String insertFestivalDataSpots(@RequestParam String areacode) {
        List<FestivalData> spots = touristService.fetchFestivalSpots(areacode);
        for (FestivalData spot : spots) {
            touristRepository.saveOrUpdateFestival (spot); // 'firstimage'가 있는 관광지 정보만 데이터베이스에 저장
        }
        return "redirect:/touristSpots?areacode=" + areacode;
    }
    @PostMapping("/updateAllFestivalDataSpots")
    public String updateAllFestivalDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            List<FestivalData> spots = touristService.fetchFestivalSpots(areacode);
            for (FestivalData spot : spots) {
                touristRepository.saveOrUpdateFestival(spot);
            }
        }
        return "redirect:/touristSpots";
    }

    //레포츠
    @PostMapping("/insertSportsDataSpots")
    public String insertSportsDataSpots(@RequestParam String areacode) {
        List<SportsData> spots = touristService.fetchSportsSpots(areacode);
        for (SportsData spot : spots) {
            touristRepository.saveOrUpdateSports (spot); // 'firstimage'가 있는 관광지 정보만 데이터베이스에 저장
        }
        return "redirect:/touristSpots?areacode=" + areacode;
    }
    @PostMapping("/updateAllSportsDataSpots")
    public String updateAllSportsDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            List<SportsData> spots = touristService.fetchSportsSpots(areacode);
            for (SportsData spot : spots) {
                touristRepository.saveOrUpdateSports(spot);
            }
        }
        return "redirect:/touristSpots";
    }

    //숙박
    @PostMapping("/insertLodgmentDataSpots")
    public String insertLodgmentDataSpots(@RequestParam String areacode) {
        List<LodgmentData> spots = touristService.fetchLodgmentSpots(areacode);
        for (LodgmentData spot : spots) {
            touristRepository.saveOrUpdateLodgment (spot); // 'firstimage'가 있는 관광지 정보만 데이터베이스에 저장
        }
        return "redirect:/touristSpots?areacode=" + areacode;
    }
    @PostMapping("/updateAllLodgmentDataSpots")
    public String updateAllLodgmentDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            List<LodgmentData> spots = touristService.fetchLodgmentSpots(areacode);
            for (LodgmentData spot : spots) {
                touristRepository.saveOrUpdateLodgment(spot);
            }
        }
        return "redirect:/touristSpots";
    }

    //쇼핑
    @PostMapping("/insertShoppingDataSpots")
    public String insertShoppingDataSpots(@RequestParam String areacode) {
        List<ShoppingData> spots = touristService.fetchShoppingSpots(areacode);
        for (ShoppingData spot : spots) {
            touristRepository.saveOrUpdateShopping (spot); // 'firstimage'가 있는 관광지 정보만 데이터베이스에 저장
        }
        return "redirect:/touristSpots?areacode=" + areacode;
    }
    @PostMapping("/updateAllShoppingDataSpots")
    public String updateAllShoppingDataSpots() {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            List<ShoppingData> spots = touristService.fetchShoppingSpots(areacode);
            for (ShoppingData spot : spots) {
                touristRepository.saveOrUpdateShopping(spot);
            }
        }
        return "redirect:/touristSpots";
    }
}
