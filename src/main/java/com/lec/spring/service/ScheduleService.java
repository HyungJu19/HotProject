package com.lec.spring.service;

import com.lec.spring.domain.Schedule;

import java.util.List;

public interface ScheduleService {

    void savePlace(Schedule schedule);
    List<Schedule> getAllPlaces();

}
