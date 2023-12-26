package com.lec.spring.service;

import com.lec.spring.domain.Schedule;
import com.lec.spring.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{



    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public void savePlace(Schedule schedule) {
        scheduleRepository.toursava(schedule);

    }

    @Override
    public List<Schedule> getAllPlaces() {
        return scheduleRepository.selectAll();
    }
}
