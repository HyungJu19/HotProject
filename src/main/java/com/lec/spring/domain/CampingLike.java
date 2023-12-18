package com.lec.spring.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class CampingLike {
    @EmbeddedId
    private TourLikeList TourLikeList;
}
