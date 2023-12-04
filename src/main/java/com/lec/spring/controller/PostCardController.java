package com.lec.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//광현

@Controller
@RequestMapping("/postCard")
public class PostCardController {

    @GetMapping("/main")
    public void main(){}
}
