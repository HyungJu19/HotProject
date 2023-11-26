package com.lec.spring.controller;


import com.lec.spring.domain.*;
import com.lec.spring.repository.TouristRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//민호
@Controller
@RequestMapping("/")
public class HomeController {

    private TouristRepository touristRepository;


    public HomeController(SqlSession sqlSession){
        touristRepository = sqlSession.getMapper(TouristRepository.class);
    }


    @RequestMapping("/")
    public String home(){
        return "redirect:/home";

    }


    @GetMapping("/home")
    public String home(Model model, @RequestParam(defaultValue = "1") int page){

        int limit = 20;
        int offset = (page - 1) * limit;
        List<TouristData> touristData = touristRepository.touristFindAll(limit, offset);
        List<RestaurantData> restaurantData = touristRepository.restauranFindAll(limit, offset);
        List<CulturalData> culturalData = touristRepository.culturalFindAll(limit, offset);
        List<FestivalData> festivalData = touristRepository.festivalFindAll(limit, offset);
        List<SportsData> sportsData = touristRepository.sportFindAll(limit, offset);
        List<LodgmentData> lodgmentData = touristRepository.lodgmentFindAll(limit, offset);
        List<ShoppingData> shoppingData = touristRepository.shoppingFindAll(limit, offset);


        model.addAttribute("touristData",touristData);
        model.addAttribute("restaurantData",restaurantData);
        model.addAttribute("culturalData",culturalData);
        model.addAttribute("festivalData",festivalData);
        model.addAttribute("sportsData",sportsData);
        model.addAttribute("lodgmentData",lodgmentData);
        model.addAttribute("shoppingData",shoppingData);


        int totalTouristData = touristRepository.countTouristData();
        int totalRestauranData = touristRepository.countRestauranData();
        int totalCulturalData = touristRepository.countCulturalData();
        int totaFestivalData = touristRepository.countFestivalData();
        int totaSporttData = touristRepository.countSporttData();
        int totaLodgmentData = touristRepository.countLodgmentData();
        int totashoppingData = touristRepository.countShoppingData();

        int maxPageTourist = (totalTouristData + limit - 1) / limit;
        int maxPageRestauran = (totalRestauranData + limit - 1) / limit;
        int maxPageCultural = (totalCulturalData + limit - 1) / limit;
        int maxPageFestival = (totaFestivalData + limit - 1) / limit;
        int maxPageSportt = (totaSporttData + limit - 1) / limit;
        int maxPageLodgment = (totaLodgmentData + limit - 1) / limit;
        int maxPagehopping = (totashoppingData + limit - 1) / limit;

        model.addAttribute("maxPageTourist", maxPageTourist);
        model.addAttribute("maxPageRestauran", maxPageRestauran);
        model.addAttribute("maxPageCultural", maxPageCultural);
        model.addAttribute("maxPageFestival", maxPageFestival);
        model.addAttribute("maxPageSportt", maxPageSportt);
        model.addAttribute("maxPageLodgment", maxPageLodgment);
        model.addAttribute("maxPagehopping", maxPagehopping);


        model.addAttribute("currentPage", page);
        return "home";
    };
    @RequestMapping("/index")
    public void index(Model model){};


}
