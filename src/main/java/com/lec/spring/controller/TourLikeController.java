package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.TourLikeList;
import com.lec.spring.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TourLikeController {


    @Autowired
    private TouristRepository touristRepository;


    @GetMapping("/api/getLikeStatus")
    public ResponseEntity<List<TourLikeList>> getLikeStatus(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long uid = principalDetails.getUser().getUid();
        List<TourLikeList> tourLikeList = touristRepository.findByLike(uid);

        System.out.println("나와랏!" + uid);
        System.out.println(tourLikeList);

        return ResponseEntity.ok().body(tourLikeList);
    }
}