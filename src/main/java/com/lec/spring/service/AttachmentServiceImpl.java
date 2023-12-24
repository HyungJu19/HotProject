package com.lec.spring.service;

import com.lec.spring.domain.Attachment;
import com.lec.spring.repository.AttachmentRepository;
import com.lec.spring.repository.PostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    private PostRepository postRepository;
    @Autowired
    public AttachmentServiceImpl(SqlSession sqlSession){
        attachmentRepository = sqlSession.getMapper(AttachmentRepository.class);
        postRepository = sqlSession.getMapper(PostRepository.class);

    }

    @Override
    public Attachment findById(Long uid) {
        return attachmentRepository.findById(uid);
    }




}








