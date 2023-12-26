package com.lec.spring.repository;

import com.lec.spring.domain.PostCardData;
import org.springframework.transaction.annotation.Transactional;

public interface PostCardRepository {

    @Transactional
    int postCardSave(PostCardData postCardData);

}
