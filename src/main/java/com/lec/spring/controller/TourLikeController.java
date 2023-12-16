package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.TourLikeList;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourLikeController {


    @Autowired
    private TouristRepository touristRepository;

    @Autowired
    private TouristService touristService;


    @PostMapping("/api/getLikeStatus")
    public ResponseEntity<List<TourLikeList>> getLikeStatus(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long uid = principalDetails.getUser().getUid();
        List<TourLikeList> tourLikeList = touristRepository.findByLike(uid);

        System.out.println("나와랏!" + uid);
        System.out.println(tourLikeList);

        return ResponseEntity.ok().body(tourLikeList);
    }


    @GetMapping("/api/getLikeStatus1/{tourId}")
    public int getLikeStatus1(@PathVariable Long tourId,Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long uid = principalDetails.getUser().getUid();

        return touristService.getLike(uid,tourId);
    }

    @GetMapping("/api/getLikeCount/{tourId}")
    @ResponseBody
    public int getLikeCount(@PathVariable Long tourId) {
        int likeCount = touristRepository.getLikeCount(tourId);
        return likeCount;
    }


}