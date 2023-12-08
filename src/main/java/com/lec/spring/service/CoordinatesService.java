package com.lec.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class CoordinatesService {

    private final Map<String, Coordinates> coordinatesMap = new HashMap<>();

    public CoordinatesService() {
        coordinatesMap.put("서울", new Coordinates(60, 127));
        coordinatesMap.put("경기", new Coordinates(60, 120));
        coordinatesMap.put("인천", new Coordinates(55, 124));
        coordinatesMap.put("강원", new Coordinates(73, 134));
        coordinatesMap.put("충남", new Coordinates(68, 100));
        coordinatesMap.put("세종", new Coordinates(66, 103));
        coordinatesMap.put("대전", new Coordinates(67, 100));
        coordinatesMap.put("충북", new Coordinates(69, 107));
        coordinatesMap.put("광주", new Coordinates(58, 74));
        coordinatesMap.put("전남", new Coordinates(51, 67));
        coordinatesMap.put("전북", new Coordinates(63, 89));
        coordinatesMap.put("경북", new Coordinates(89, 91));
        coordinatesMap.put("대구", new Coordinates(89, 90));
        coordinatesMap.put("경남", new Coordinates(91, 77));
        coordinatesMap.put("울산", new Coordinates(102, 84));
        coordinatesMap.put("부산", new Coordinates(98, 76));
        coordinatesMap.put("제주", new Coordinates(52, 38));
    }

    public Coordinates getCoordinates(String region) {
        return coordinatesMap.get(region);
    }

    public static class Coordinates {
        private int latitude;
        private int longitude;

        public Coordinates(int latitude, int longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public int getLatitude() {
            return latitude;
        }

        public int getLongitude() {
            return longitude;
        }
    }
}


