package com.lec.spring.controller;


import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//민호
@Controller
@RequestMapping("/")
public class HomeController {

    private TouristRepository touristRepository;

    private TouristService touristService;


    public HomeController(TouristRepository touristRepository, TouristService touristService){
        this.touristRepository = touristRepository;
        this.touristService = touristService;
    }


    @GetMapping("/home")
    public void home(){


    }



    @GetMapping("/index")
    public void index(Model model){};


    @GetMapping("/search")
    public String search(
            @RequestParam("areaCode") String areaCode,
            @RequestParam("contentTypeId") String contentTypeId,
            @RequestParam(value = "page",defaultValue = "1") int page,
            Model model) {

        int limit = 4;
        int offset = (page - 1) * limit;

        List<TouristData> dataList = touristService.touristDataList(areaCode,contentTypeId,limit,offset);
        model.addAttribute("dataList", dataList);



        return "search";
    }
}
