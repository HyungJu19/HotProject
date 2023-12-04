package com.lec.spring.controller;


import com.lec.spring.domain.CampingData;
import com.lec.spring.repository.CampingRepository;
import com.lec.spring.service.CampingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


//재환
@Controller
@RequestMapping("/")
public class CampingController {

    @Autowired
    private CampingService campingService;

    @Autowired
    private CampingRepository campingRepository;

    private static final Logger logger = LoggerFactory.getLogger(CampingController.class);

    @GetMapping("/campingSpots")
    public String viweCampingSpots(Model model) {
        return "campingSpots";  // 캠핑정보를 담은 뷰 반환
    }

    @GetMapping("/updateAllCampingSpots")
    public String updateAllCampingSpots() {
        try {
            // 캠핑 서비스에서 모든 캠핑장 데이터를 가져와 데이터베이스에 저장
            List<CampingData> campingSpots = campingService.fetchCampingSpots();
            for (CampingData spot : campingSpots) {
                campingRepository.saveOrUpdateCamping(spot);
            }
        } catch (Exception e) {
            // 로깅 강화: 실패한 경우에 대한 로그 기록
            logger.error("Error updating all camping spots", e);
        }

        // 모든 캠핑장 업데이트가 완료되면 캠핑장 페이지로 리다이렉트
        return "redirect:/campingSpots";
    }



        @GetMapping("/theme/camping/main")
        public void main() {
        }


    }


