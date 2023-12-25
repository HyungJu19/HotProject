package com.lec.spring.controller;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.Schedule;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.ScheduleRepository;
import com.lec.spring.service.ScheduleService;
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
@RequestMapping("/schedule")
public class TourPlanController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TouristServiceImpl touristService;
    @Autowired
    private KakaoMapProperties kakaoMapProperties;

    @GetMapping("/tour")
    public String search(HttpServletRequest request, Model model) {
        String area = request.getParameter("area");
        String areaCode = request.getParameter("areaCode");
        String contentTypeId = request.getParameter("contentTypeId");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        String orderby = request.getParameter("oderby");
        int limit = 20;
        int offset = (page - 1) * limit;
        List<TouristData> dataList = touristService.touristDataList1(area, areaCode, contentTypeId,orderby, limit, offset);
        model.addAttribute("dataList", dataList);

        List<CampingData> campingDataList = touristService.campingDataList(area, areaCode, orderby, limit, offset);
        model.addAttribute("campingDataList", campingDataList);

        int totalItems = touristService.getTotalDataCount(areaCode, contentTypeId);
        int totalPages = (int) Math.ceil((double) totalItems / limit);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("contentTypeId", contentTypeId);


        String kakaoMapApiKey = kakaoMapProperties.getApikey();
        model.addAttribute("kakaoMapApiKey", kakaoMapApiKey);
        int totalCount = touristService.getTotalAreacodeCount(areaCode, contentTypeId);
        model.addAttribute("totalCount", totalCount);
        int campingTotalCount = touristService.getConpingAreaTotalCount(area);
        model.addAttribute("campintTotalCount", campingTotalCount);
        System.out.println(dataList + "????");

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

        return "schedule/tour";

    }


    @GetMapping("save")
    public String toursave (){

        return null;

    }
    @GetMapping("save")
    public String toursaveOk (){

        return null;

    }

    @GetMapping("delete")
    public String deleteOk(){

        return null;
    }


    @GetMapping("tourList")
    public String tourList (HttpServletRequest request,Model model){
        List<Schedule> places =scheduleService.getAllPlaces();
        model.addAttribute("places", places);



        return "schedule/tourList";
    }


}

