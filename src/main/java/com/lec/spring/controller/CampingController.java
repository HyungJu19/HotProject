package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//재환
@Controller
@RequestMapping("/theme/camping")
public class CampingController {
    @GetMapping("/main")
    public void main(){}

}

