package com.lec.spring.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResult {

    private List<Post> posts;
    private List<TouristData> touristDataList;
    private List<CampingData> campingDataList;

}
