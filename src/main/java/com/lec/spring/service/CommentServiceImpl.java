package com.lec.spring.service;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.User;
import com.lec.spring.repository.CommentRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(SqlSession sqlSession){
        commentRepository = sqlSession.getMapper(CommentRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);

        System.out.println("CommentService() 생성");
    }


    @Override
    public QryCommentList list(Long postId) {
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findByPost(postId);

        list.setCount(comments.size());   // 댓글의 개수
        list.setList(comments);
        list.setStatus("OK");

        return list;
    }

    @Override
    public QryResult write(Long postId, Long uid, String content) {

        User user = userRepository.findById(uid);

        Comment comment = Comment.builder()
                .user(user)
                .comment(content)
                .postId(postId)
                .build();

        System.out.println(comment);
        commentRepository.commentSave(comment);

        QryResult result = QryResult.builder()
                .count(1)
                .status("OK")
                .build();


        return result;
    }

    @Override
    public QryResult delete(Long postId, Long uid) {
        int count = commentRepository.deleteById(postId, uid);
        String status = "FAIL";

        if(count > 0) status = "OK";

        QryResult result = QryResult.builder()
                .count(count)
                .status(status)
                .build();

        return result;
    }
}
