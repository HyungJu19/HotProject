package com.lec.spring.controller;


import com.lec.spring.repository.TouristRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//민호
@Controller
@RequestMapping("/")
public class HomeController {

    private TouristRepository touristRepository;


    public HomeController(SqlSession sqlSession){
        touristRepository = sqlSession.getMapper(TouristRepository.class);
    }


    @GetMapping("/home")
    public void home(){


    }



    @GetMapping("/index")
    public void index(Model model){};


}
