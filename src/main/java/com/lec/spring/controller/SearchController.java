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
import org.springframework.web.bind.annotation.*;

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
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;


        int limit = 20;
        int offset = (page - 1) * limit;


        List<TouristData> titleimg = touristRepository.findBytourContentId(contentId);
        model.addAttribute("titleimg", titleimg);




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


        List<CampingData> campingDataList = touristService.campingDataList(area, areaCode, limit, offset);
        model.addAttribute("campingDataList", campingDataList);

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

    @PostMapping("/likeOk/{tourId}")
    @ResponseBody
    public String likeTour (@PathVariable Long tourId, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long uid = principalDetails.getUser().getUid(); // 사용자 아이디 (일반적으로 getUsername() 메서드를 통해 가져옵니다)
            // 여기서 username을 이용하여 해당 사용자의 ID를 가져오는 로직을 추가해야 합니다.
            // 예를 들어, UserService를 통해 username에 해당하는 사용자의 ID를 가져오는 방법을 사용할 수 있습니다.
            System.out.println("나와랏ok "+ uid);
//            Long userId = userService.findByuid(username);
//            System.out.println(userId);
            userService.likeTour(uid, tourId);
        }
        return "ok";
    }
    @PostMapping("/likeX/{tourId}")
    @ResponseBody
    public String unlikeTour(@PathVariable Long tourId, Authentication authentication ) {
        if (authentication != null && authentication.isAuthenticated()) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long uid = principalDetails.getUser().getUid(); // 사용자 아이디 (일반적으로 getUsername() 메서드를 통해 가져옵니다)
            // 여기서 username을 이용하여 해당 사용자의 ID를 가져오는 로직을 추가해야 합니다.
            // 예를 들어, UserService를 통해 username에 해당하는 사용자의 ID를 가져오는 방법을 사용할 수 있습니다.
            System.out.println("나와랏x "+ uid);
//            Long userId = userService.findByuid(username);
//            System.out.println(userId);
            userService.unlikeTour(uid, tourId);
        }
        return "x";
    }


}




