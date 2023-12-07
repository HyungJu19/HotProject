package com.lec.spring.service;

import com.lec.spring.domain.WeatherInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface WeatherService {

    // 단기예보
        WeatherInfo getShortTermForecast(String location, LocalDate startDate, LocalDate endDate);
    // 중기예보 날씨예보
        WeatherInfo getMidTermWeather(String location, LocalDate startDate, LocalDate endDate);
    // 중기예보 기온예보
        WeatherInfo getMidTermTemperature(String location, LocalDate startDate, LocalDate endDate);

}
