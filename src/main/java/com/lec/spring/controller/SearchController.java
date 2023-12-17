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
@RequestMapping("/search")
public class SearchController {
    private TouristRepository touristRepository;

    private TouristService touristService;

    private BoardService boardService;


    public SearchController(TouristRepository touristRepository, TouristService touristService, BoardService boardService) {
        this.touristRepository = touristRepository;
        this.touristService = touristService;
        this.boardService = boardService;
    }


    @GetMapping
    public String search(HttpServletRequest request, String contentId, Model model) {


            String area = request.getParameter("area");
            String areaCode = request.getParameter("areaCode");
            String contentTypeId = request.getParameter("contentTypeId");
            int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;


            int limit = 20;
            int offset = (page - 1) * limit;


            List<TouristData> titleimg= touristRepository.findBytourContentId(contentId);
            model.addAttribute("titleimg",titleimg);



            List<TouristData> dataList = touristService.touristDataList(area, areaCode, contentTypeId, limit, offset);
            model.addAttribute("dataList", dataList);


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


            String keyword = request.getParameter("keyword");
            List<CampingData> campingSearchData = touristService.campingSearchData(keyword);
            model.addAttribute("campingSearchData", campingSearchData);
            List<TouristData> tourSearchData = touristService.tourSearchData(keyword);
            model.addAttribute("tourSearchData", tourSearchData);
            List<Post> postSearchData = boardService.boardSearchData(keyword);
            model.addAttribute("postSearchData", postSearchData);




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
    }

