package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardRepository {

    // 새 글 작성 (INSERT) <- Post
    int save(Post post);

    // 특정 id 글 내용 읽기 (SELECT)
    // 만약 해당 id 의 글 없으면 null 리턴함
    Post findById(Long id);

    // 특정 id 글 조회수 +1 증가 (UPDATE)
    int incViewCnt(Long id);

    // 전체 글 목록 : 최신순 (SELECT)
    List<Post> findAll();

    // 특정 id 글 수정 (제목, 내용) (UPDATE)
    int update(Post post);

    // 특정 id 글 삭제하기 (DELETE)
    int delete(Post post);


    List<Post> boardSearch(String keyword, int limit, int offset);

//    List<Post> getTotalTourPost(
//            @Param("area") String area
//            ,@Param("areaCode") String areaCode
//            ,@Param("contentTypeId") String contentTypeId
//            ,@Param("orderby") String orderby
//            ,@Param("limit") int limit
//            ,@Param("offset") int offset);
//
//    List<Post> getTotalCampingPost(
//            @Param("area") String area
//            ,@Param("areaCode") String areaCode
//            ,@Param("orderby") String orderby
//            ,@Param("limit") int limit
//            ,@Param("offset") int offset);
//
}
