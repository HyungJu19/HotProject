package com.lec.spring.service;

import com.lec.spring.domain.CampingData;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface CampingService {
    List<CampingData> fetchCampingSpots() throws UnsupportedEncodingException;
}
