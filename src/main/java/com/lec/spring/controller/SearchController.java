package com.lec.spring.controller;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private TouristRepository touristRepository;

    private TouristService touristService;


    public SearchController(TouristRepository touristRepository, TouristService touristService){
        this.touristRepository = touristRepository;
        this.touristService = touristService;
    }


        @GetMapping
        public String search(HttpServletRequest request, Model model) {
        String area = request.getParameter("area");
        String areaCode = request.getParameter("areaCode");
        String contentTypeId = request.getParameter("contentTypeId");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;


        int limit = 20;
        int offset = (page - 1) * limit;

        List<TouristData> dataList = touristService.touristDataList(area,areaCode,contentTypeId,limit,offset);
        model.addAttribute("dataList", dataList);

        List<CampingData> campingDataList = touristService.campingDataList(area,areaCode,limit,offset);
        model.addAttribute("campingDataList",campingDataList);

        int campingTotalCount = touristService.getConpingAreaTotalCount(area);
        model.addAttribute("campintTotalCount",campingTotalCount);


        int totalCount = touristService.getTotalAreacodeCount(areaCode,contentTypeId);
        model.addAttribute("totalCount",totalCount);

        String buttonText = "";

        if (contentTypeId == null) {
            buttonText = "캠핑";
        } else {
            if (contentTypeId.equals("12")) {
                buttonText = "관광지";
            } else if (contentTypeId.equals("39")) {
                buttonText = "음식점";
            } else if (contentTypeId.equals("32")) {
                buttonText = "숙박";
            } else if (contentTypeId.equals("38")) {
                buttonText = "쇼핑";
            } else if (contentTypeId.equals("28")) {
                buttonText = "레포츠";
            } else if (contentTypeId.equals("15")) {
                buttonText = "축제";
            } else if (contentTypeId.equals("14")) {
                buttonText = "문화시설";
            }
        }
        model.addAttribute("buttonText", buttonText);


        return "search";
    }
}
