package com.lec.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//민호
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @GetMapping("/main")
    public void main (){};

}
