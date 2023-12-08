package com.lec.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//민호
@Controller
@RequestMapping("/")
public class HomeController {



    @GetMapping("/home")
    public void home(){


    }



    @GetMapping("/index")
    public void index(Model model){};



}
