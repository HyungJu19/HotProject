package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.CampingLikeList;
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

//투어
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
        return touristRepository.getLikeCount(tourId);
    }

//캠핑
@PostMapping("/api/getLikeCamStatus")
public ResponseEntity<List<CampingLikeList>> getLikeCampingStatus(Authentication authentication) {
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    Long uid = principalDetails.getUser().getUid();
    List<CampingLikeList> campingLikeLists = touristRepository.findBycamLike(uid);

    System.out.println("나와랏!" + uid);
    System.out.println(campingLikeLists);

    return ResponseEntity.ok().body(campingLikeLists);
}


    @GetMapping("/api/getLikeStatus2/{camping_id}")
    public int getLikeCampingStatus1(@PathVariable Long camping_id,Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long uid = principalDetails.getUser().getUid();

        return touristService.getcamLike(uid,camping_id);
    }

    @GetMapping("/api/getLikeCamCount/{campingid}")
    @ResponseBody
    public int getLikeCampingCount(@PathVariable Long campingid) {
        return touristRepository.getCamLikeCount(campingid);
    }

}