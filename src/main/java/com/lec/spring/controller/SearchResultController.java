package com.lec.spring.controller;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.TouristService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/searchResult")
public class SearchResultController {

    private TouristService touristService;

    private BoardService boardService;

    public SearchResultController(TouristService touristService, BoardService boardService) {

        this.touristService = touristService;
        this.boardService = boardService;
    }

    @GetMapping
    public String search(HttpServletRequest request, String contentId, Model model){
        String area = request.getParameter("area");
        String areaCode = request.getParameter("areaCode");
        String contentTypeId = request.getParameter("contentTypeId");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;


        int limit = 20;
        int offset = (page - 1) * limit;

        model.addAttribute("page", page);
        List<CampingData> campingDataList = touristService.campingDataList(area, areaCode, limit, offset);
        model.addAttribute("campingDataList", campingDataList);

        int campingTotalCount = touristService.getConpingAreaTotalCount(area);
        model.addAttribute("campintTotalCount", campingTotalCount);



        // 서치쿼리
        String keyword = request.getParameter("keyword");

        // 서치쿼리 페이징
        int totalSearchcamping = touristService.getTotalCampingSearchDataCount(keyword);
        int totalSearchtour = touristService.getTotalTourSearchDataCount(keyword);
        int totalCampingPages = (int) Math.ceil((double) totalSearchcamping/ limit);
        int totalTourPages = (int) Math.ceil((double) totalSearchtour/ limit);
        model.addAttribute("totalCampingPages", totalCampingPages);
        model.addAttribute("totalTourPages", totalTourPages);

        // 캠핑서치
        List<CampingData> campingSearchData = touristService.campingSearchData(keyword, limit, offset);
        model.addAttribute("campingSearchData", campingSearchData);
        // 투어서치
        List<TouristData> tourSearchData = touristService.tourSearchData(keyword, limit, offset);
        model.addAttribute("tourSearchData", tourSearchData);
        // 게시판서치
        List<Post> postSearchData = boardService.boardSearchData(keyword);
        model.addAttribute("postSearchData", postSearchData);


        return "searchResult";
    }

}
