package com.lec.spring.controller;

import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//기원
@Controller
@RequestMapping("/theme/foodie/main")
public class FoodieController {


    private TouristService touristservice;
    public FoodieController(TouristService touristservice) {
        this.touristservice = touristservice;
    }
//    @GetMapping()
//    public String search(HttpServletRequest request, Model model) {
//        String areaCode = request.getParameter("areaCode");
//        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
//        int limit = 4;
//        int offset = (page - 1) * limit;
//        List<TouristData> dataList = touristservice.foodDataList(areaCode, limit, offset);
//        model.addAttribute("dataList", dataList);
//
//return "theme/foodie/main";
//    }
@GetMapping()
    public String local(HttpServletRequest request, Model model) {
        String areaCode = request.getParameter("areaCode");
        String  sigungucode = request.getParameter("sigungucode");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int limit = 4;
        int offset = (page - 1) * limit;
        List<TouristData> dataList = touristservice.localfoodie(areaCode, sigungucode, limit, offset);
        model.addAttribute("dataList", dataList);

        return "theme/foodie/main";
    }


}