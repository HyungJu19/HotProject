package com.lec.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/festival")
public class FestivalController {

    @GetMapping("/list")
    public void list(Model model){

    }
}
