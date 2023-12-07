package com.lec.spring.controller;

import com.lec.spring.domain.WeatherInfo;
import com.lec.spring.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/showWeather")
    public String showWeather(@RequestParam String location, Model model) {
        // 사용자가 선택한 지역(location)의 날씨 정보 가져오기
        WeatherInfo weatherInfo = weatherService.getShortTermForecast(location, LocalDate.now(), LocalDate.now());

        // 날씨 정보를 모델에 추가
        model.addAttribute("weatherInfo", weatherInfo);

        // 캘린더 화면으로 이동
        return "calendar.html"; // 이동할 뷰의 이름 (calendar.html)
    }
}
