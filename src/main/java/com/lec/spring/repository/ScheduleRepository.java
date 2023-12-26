package com.lec.spring.repository;

import com.lec.spring.domain.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleRepository {
    void toursava(Schedule schedule);
    List<Schedule> selectAll();

}
