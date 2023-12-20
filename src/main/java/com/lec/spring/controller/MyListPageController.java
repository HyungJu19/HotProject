package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.TourLikeList;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyListPageController {

    @Autowired
    private TouristRepository touristRepository;

    @Autowired
    private TouristService touristservice;

    @GetMapping("/user/mylist")
    public String likelist(Model model,Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long uid = principalDetails.getUser().getUid();

        List<TouristData> myTourCntAll = touristservice.myTourCntAll(uid);
        model.addAttribute("myTourCntAll",myTourCntAll);
        List<Post> myPostList = touristservice.myPostList(uid);
        model.addAttribute("myPostList", myPostList);

        return "user/mylist";
    }



}
