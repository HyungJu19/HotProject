package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//기원
@Controller
@RequestMapping("/theme/foodie")
public class FoodieController {
    @GetMapping("/main")
    public void main(){}
}
