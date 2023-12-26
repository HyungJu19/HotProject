package com.lec.spring.controller;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.TouristData;
import com.lec.spring.service.TouristServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/schedule/tour")
public class TourPlanController {

    @Autowired
    public TouristServiceImpl touristService;
    @Autowired
    private KakaoMapProperties kakaoMapProperties;

    @GetMapping
    public String search(HttpServletRequest request, Model model) {
        String area = request.getParameter("area");
        String areaCode = request.getParameter("areaCode");
        String contentTypeId = request.getParameter("contentTypeId");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        String orderby = request.getParameter("oderby");
        int limit = 20;
        int offset = (page - 1) * limit;
        List<TouristData> dataList = touristService.touristDataList(area, areaCode, contentTypeId, limit, offset);
        model.addAttribute("dataList", dataList);

        List<CampingData> campingDataList = touristService.campingDataList(area, areaCode, orderby, limit, offset);
        model.addAttribute("campingDataList", campingDataList);


        String kakaoMapApiKey = kakaoMapProperties.getApikey();
        model.addAttribute("kakaoMapApiKey", kakaoMapApiKey);


        System.out.println(dataList + "????");

        return "schedule/tour";

    }


}

