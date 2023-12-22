package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import com.lec.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private TouristRepository touristRepository;

    private TouristService touristService;

    @Autowired
    private UserService userService;

    public SearchController(TouristRepository touristRepository, TouristService touristService) {
        this.touristRepository = touristRepository;
        this.touristService = touristService;
    }






    @GetMapping
    public String search(HttpServletRequest request, HttpSession session, String contentId, Model model) {




        String username = (String) session.getAttribute("nameuser");

        if(username != null){
            model.addAttribute("logged",true);
            model.addAttribute("username",username);
            System.out.println(username);
        } else {
            model.addAttribute("logged",false);
        }

        String area = request.getParameter("area");
        String areaCode = request.getParameter("areaCode");
        String contentTypeId = request.getParameter("contentTypeId");
        String orderby = request.getParameter("orderby");


        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;


        int limit = 20;
        int offset = (page - 1) * limit;


        List<TouristData> titleimg = touristRepository.findBytourContentId(contentId);
        model.addAttribute("titleimg", titleimg);


        List<TouristData> dataList1 = touristService.touristDataList1(area, areaCode, contentTypeId, orderby, limit, offset);
        model.addAttribute("dataList1", dataList1);

        List<TouristData> dataList = touristService.touristDataList(area, areaCode, contentTypeId, limit, offset);
        model.addAttribute("dataList", dataList);

        System.out.println("니가범인 " + dataList );
        model.addAttribute("page", page);


        int totalItems = touristService.getTotalDataCount(areaCode, contentTypeId);
        int totalPages = (int) Math.ceil((double) totalItems / limit);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("contentTypeId", contentTypeId);



        List<CampingData> campingDataList = touristService.campingDataList(area, areaCode, orderby, limit, offset);
        model.addAttribute("campingDataList", campingDataList);

        System.out.println(campingDataList);
        int campingTotalCount = touristService.getConpingAreaTotalCount(area);
        model.addAttribute("campintTotalCount", campingTotalCount);


        int totalCount = touristService.getTotalAreacodeCount(areaCode, contentTypeId);
        model.addAttribute("totalCount", totalCount);

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
//투어
    @RequestMapping("/totalView/{contentId}")
    @ResponseBody
    public int totalView(@PathVariable String contentId){
        int totalView = touristRepository.totalView(contentId);
        System.out.println(totalView);
        return totalView;

    }


    @RequestMapping("/viewCount/{contentId}")
    @ResponseBody
    public String viCnt(@PathVariable String contentId) {
        try {
            touristRepository.incViewCnt(contentId);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
//캠핑
    @RequestMapping("/totalCamView/{contentId}")
    @ResponseBody
    public int totaCamlView(@PathVariable String contentId){
        int totalCamView = touristRepository.totalCamView(contentId);
        System.out.println(totalCamView);
        return totalCamView;

    }


    @RequestMapping("/viewCamCount/{contentId}")
    @ResponseBody
    public String viCamCnt(@PathVariable String contentId) {
        try {
            touristRepository.incViewCamCnt(contentId);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


//    투어
    @RequestMapping("/likeOk/{tourId}")
    @ResponseBody
    public String likeTour (@PathVariable Long tourId, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long uid = principalDetails.getUser().getUid();

            System.out.println("나와랏ok "+ uid);

            userService.likeTour(uid, tourId);
        }
        return "ok";
    }
    @RequestMapping("/likeX/{tourId}")
    @ResponseBody
    public String unlikeTour(@PathVariable Long tourId, Authentication authentication ) {
        if (authentication != null && authentication.isAuthenticated()) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long uid = principalDetails.getUser().getUid();

            System.out.println("나와랏x "+ uid);

            userService.unlikeTour(uid, tourId);
        }
        return "x";
    }


//    캠핑

    @RequestMapping("/likeOk1/{campingid}")
    @ResponseBody
    public String likeCamping (@PathVariable Long campingid, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long uid = principalDetails.getUser().getUid();

            System.out.println("나와랏ok "+ uid);

            userService.likeCamping(uid, campingid);
        }
        return "ok";
    }
    @RequestMapping("/likeX1/{campingid}")
    @ResponseBody
    public String unlikeCamping(@PathVariable Long campingid, Authentication authentication ) {
        if (authentication != null && authentication.isAuthenticated()) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long uid = principalDetails.getUser().getUid();

            System.out.println("나와랏x "+ uid);

            userService.unlikeCamping(uid, campingid);
        }
        return "x";
    }
}




