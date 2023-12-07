package com.lec.spring.controller;

import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;


//민호
@Controller
@RequestMapping("/")
public class TouristController {

    @Autowired
    private TouristService touristService;

    @Autowired
    private TouristRepository touristRepository;
    private static final Logger logger = LoggerFactory.getLogger(TouristController.class);

    @GetMapping("/touristSpots")
    public String viewTouristSpots( Model model) {



        return "touristSpots"; // 관광지 정보를 담은 뷰 반환
    }




    //관광지

    @GetMapping("/updateAllTouristSpots")
    public String updateAllTouristSpots() throws UnsupportedEncodingException {
        List<String> allAreaCodes = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39");
        for (String areacode : allAreaCodes) {
            try {
                updateTouristSpotsForArea(areacode);
            } catch (Exception e) {
                // 로깅 강화: 실패한 경우에 대한 로그 기록
                logger.error("Error updating tourist spots for area code " + areacode, e);
            }
        }
        return "redirect:/touristSpots";
    }
    @Async
    @Transactional
    public void updateTouristSpotsForArea(String areacode) throws UnsupportedEncodingException {
        List<TouristData> spots = touristService.fetchTouristSpots(areacode);
        for (TouristData spot : spots) {
            touristRepository.saveOrUpdateTourist(spot);
        }
    }





    @GetMapping("/updateAllCampingSpots")
    public String updateAllCampingSpots() {
        try {
            // 캠핑 서비스에서 모든 캠핑장 데이터를 가져와 데이터베이스에 저장
            List<CampingData> campingSpots = touristService.fetchCampingSpots();
            for (CampingData spot : campingSpots) {
                touristRepository.saveOrUpdateCamping(spot);
            }
        } catch (Exception e) {
            // 로깅 강화: 실패한 경우에 대한 로그 기록
            logger.error("Error updating all camping spots", e);
        }

        // 모든 캠핑장 업데이트가 완료되면 캠핑장 페이지로 리다이렉트
        return "redirect:/touristSpots";
    }



    @GetMapping("/theme/camping/main")
    public String main(Model model) {
        List<CampingData> campingSpots = touristRepository.campingFindAll(4,0);
        model.addAttribute("campingSpots", campingSpots);

        return "theme/camping/main";  // 이미지 갤러리 뷰 반환
    }



}