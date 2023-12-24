package com.lec.spring.controller;

import com.lec.spring.domain.CampingData;
import com.lec.spring.service.TouristService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


//재환
@Controller
@RequestMapping("/theme/camping")
public class CampingController {

    @Autowired
    private TouristService touristService;


    @GetMapping("/main")
    public String main(HttpServletRequest request, Model model) {

        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int limit = 4;
        int offset = (page - 1) * limit;
        String induty = request.getParameter("induty");
        String lctCl = request.getParameter("lctCl");


        List<CampingData> campingDataList = touristService.campingList(induty,lctCl);
        model.addAttribute("campingDataList", campingDataList);
        System.out.println(campingDataList+ " @@@@@!!!이거다!!!!");

        List<CampingData> campingRecommendList = touristService.recommentList();
        model.addAttribute("campingRecommendList",campingRecommendList);


        return "theme/camping/main";
    }


}