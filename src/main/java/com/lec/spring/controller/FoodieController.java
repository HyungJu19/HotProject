package com.lec.spring.controller;

import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


//기원
@Controller
@RequestMapping("/theme/foodie/main")
public class FoodieController {

    private TouristRepository touristRepository;
    private TouristService touristService;
    public FoodieController(TouristRepository touristRepository, TouristService touristService) {
        this.touristRepository = touristRepository;
        this.touristService = touristService;
    }
    @GetMapping()
    public String search(HttpServletRequest request, Model model) {
        String areaCode = request.getParameter("areaCode");

        List<TouristData> dataList = touristService.foodDataList(areaCode);
        model.addAttribute("dataList", dataList);

return "/theme/foodie/main";
    }
}