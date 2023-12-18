package com.lec.spring.domain.DTO;

import com.lec.spring.domain.TouristData;
import lombok.Data;

import java.util.List;

@Data
public class TouristDetailResponse {
    private Response response;

    private TouristData tour; // 여기에 TouristData 객체 추가

    @Data
    public class Response {
        private Body body;
        @Data
        public class Body {
            private Items items;
            @Data
            public class Items {
                private List<Object> item;
            }
        }
    }
}

