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
        int cpage = request.getParameter("cpage") != null ? Integer.parseInt(request.getParameter("cpage")) : 1;
        int tpage = request.getParameter("tpage") != null ? Integer.parseInt(request.getParameter("tpage")) : 1;
        int ppage = request.getParameter("ppage") != null ? Integer.parseInt(request.getParameter("ppage")) : 1;


        int climit = 20;
        int coffset = (cpage - 1) * climit;

        int tlimit = 20;
        int toffset = (tpage -1) * tlimit;



        // 서치쿼리
        String keyword = request.getParameter("keyword");


        // 캠핑서치
        List<CampingData> campingSearchData = touristService.campingSearchData(keyword, climit, coffset);
        model.addAttribute("campingSearchData", campingSearchData);
        System.out.println("campingSearchData: " + campingSearchData);
        // 투어서치
        List<TouristData> tourSearchData = touristService.tourSearchData(keyword, tlimit, toffset);
        model.addAttribute("tourSearchData", tourSearchData);
        System.out.println("tourSearchData: " + tourSearchData);

        // 서치쿼리 페이징
        int totalSearchcamping = touristService.getTotalCampingSearchDataCount(keyword);
        int totalSearchtour = touristService.getTotalTourSearchDataCount(keyword);
        int totalCampingPages = (int) Math.ceil((double) totalSearchcamping/ climit);
        int totalTourPages = (int) Math.ceil((double) totalSearchtour/ tlimit);
        model.addAttribute("campingCurrentPage", cpage);
        model.addAttribute("totalCampingPages", totalCampingPages);
        model.addAttribute("totalTourPages", totalTourPages);
        int nextCampingPages = Math.min(5, totalCampingPages - cpage);
        model.addAttribute("nextCampingPages", nextCampingPages);
        model.addAttribute("tourCurrentPage", tpage);
        int nextTourPages = Math.min(5, totalTourPages - tpage);
        model.addAttribute("nextTourPages", nextTourPages);

        int limit = 5;
        int offset = (ppage -1) * limit;
         //게시판서치
        List<Post> postSearchData = boardService.boardSearchData(keyword, limit, offset);
        model.addAttribute("postSearchData", postSearchData);


        return "searchResult";
    }

}
