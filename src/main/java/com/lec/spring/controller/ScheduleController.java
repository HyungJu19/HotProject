package com.lec.spring.controller;


import com.lec.spring.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

//민호
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private TouristService touristService;

    public ScheduleController(TouristService touristService){
        this.touristService = touristService;
    }
    @GetMapping("/main")
    public String main (Model model){
        List<Map<String, Object>> citiCount = touristService.getcitiCount();
        model.addAttribute("citiCount", citiCount);
        System.out.println(citiCount);
    return "/schedule/main";
    };


    @GetMapping("/gotour")
    public String gotour (Model model){


        return "/schedule/gotour";
    }


}
