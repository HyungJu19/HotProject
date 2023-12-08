package com.lec.spring.controller;

import com.lec.spring.domain.WeatherInfo;
import com.lec.spring.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{location}")
    public String getWeatherInfo(@PathVariable String location, Model model) {
        // 서비스를 통해 단기예보 데이터 가져오기
        WeatherInfo shortTermForecast = weatherService.getShortTermForecast(location, LocalDate.now(), LocalDate.now().plusDays(3));
        model.addAttribute("shortTermForecast", shortTermForecast);

        // 서비스를 통해 중기날씨예보 데이터 가져오기
        WeatherInfo midTermWeather = weatherService.getMidTermWeather(location, LocalDate.now(), LocalDate.now().plusDays(10));
        model.addAttribute("midTermWeather", midTermWeather);

        // 서비스를 통해 중기기온예보 데이터 가져오기
        WeatherInfo midTermTemperature = weatherService.getMidTermTemperature(location, LocalDate.now(), LocalDate.now().plusDays(10));
        model.addAttribute("midTermTemperature", midTermTemperature);

        return "testcalendar";
    }

    @PostMapping("/setLocation")
    @ResponseBody
    public String setLocation(@RequestBody Map<String, String> locationMap) {
        String location = locationMap.get("location");
        try {
            // 여기서는 좌표를 찾는 대신 "서울"이라는 문자열을 그대로 반환하는 것으로 가정
            // 실제로는 좌표를 찾아서 사용해야 합니다.
            return "서울";
        } catch (IllegalArgumentException e) {
            return "error";
        }
    }
}