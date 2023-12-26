package com.lec.spring.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    private int id;
    private String uid;
    private String title;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private String contentId;
    private int duration;
}
