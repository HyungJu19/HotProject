package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//승원
@Controller
@RequestMapping("/content/festival")
public class FestivalController {



    @GetMapping("/home")
    public void home(){}
}
