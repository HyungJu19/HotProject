package com.lec.spring.controller;


import com.lec.spring.service.TouristService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


//민호
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private TouristService touristService;

    public HomeController(TouristService touristService1){
        this.touristService = touristService1;
    }

    @GetMapping
    public String home(Model model){
        List<Map<String, Object>> citiCount = touristService.getcitiCount();
        model.addAttribute("citiCount", citiCount);
        System.out.println(citiCount);
        return "home";
    }


    @GetMapping("/index")
    public void index(Model model){};


}
