package com.lec.spring.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristDetail {

    @JsonProperty("contentid")
    private String contentid;

    @JsonProperty("contenttypeid")
    private String contenttypeid;
    private String heritage1;
    private String heritage2;
    private String heritage3;
    private String infocenter;
    private String opendate;
    private String restdate;
    private String expguide;
    private String expagerange;
    private String accomcount;
    private String useseason;
    private String usetime;
    private String parking;
    private String chkbabycarriage;
    private String chkpet;
    private String chkcreditcard;


}
