package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.BoardRepository;
import com.lec.spring.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceimpl implements BoardService{

    private BoardRepository boardRepository;

    private UserRepository userRepository;

    public BoardServiceimpl(SqlSession sqlSession){
        boardRepository = sqlSession.getMapper(BoardRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);

    }

    @Override
    public List<Post> boardSearchData(String keyword, int limit, int offset) {
        return boardRepository.boardSearch(keyword, limit, offset );
    }

//    @Override
//    public List<Post> getTotalTourPost(String area, String areaCode, String contentTypeId, String orderby, int limit, int offset) {
//        return boardRepository.getTotalTourPost(area, areaCode, contentTypeId, orderby, limit, offset);
//    }
//
//    @Override
//    public List<Post> getTotalCampingPost(String area, String areaCode, String orderby, int limit, int offset) {
//        return boardRepository.getTotalCampingPost(area, areaCode, orderby, limit, offset);
//    }


//    @Override
//    public int write(Post post, Map<String, MultipartFile> files) {
////        // 현재 로그인한 작성자 정보.
////        User user = U.getLoggedUser();
////
////        // 위 정보는 session 의 정보이고, 일단 DB 에서 다시 읽어온다
////        user = userRepository.findById(user.getId());
////        post.setUser(user);   // 글 작성자 세팅
////
////        int cnt = boardRepository.save(post);
////
////        // 첨부파일 추가
//////        addFiles(files, post.getId());
////
//        return int;
//    }

//    @Override
//    @Transactional  // 이 메소드는 '트랜잭션' 처리
//    public Post detail(Long id) {
//        boardRepository.incViewCnt(id);
//        Post post = boardRepository.findById(id);
//
//        if(post != null){
//            // 첨부파일(들) 정보 가져오기
//            List<Attachment> fileList = attachmentRepository.findByPost(post.getId());
//            setImage(fileList);   // 이미지 파일 여부 세팅
//            post.setFileList(fileList);
//        }

//        return post;
//    }
//
//    @Override
//    public List<Post> list() {
//        return boardRepository.findAll();
//    }
//    // 페이징 리스트
//    @Override
//    public List<Post> list(Integer page, Model model) {
//        // 현재 페이지 parameter
//        if(page == null) page = 1;  // 디폴트는 1page
//        if(page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
//        HttpSession session = U.getSession();
//        Integer writePages = (Integer)session.getAttribute("writePages");
//        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
//        Integer pageRows = (Integer)session.getAttribute("pageRows");
//        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
//        session.setAttribute("page", page);
//
//        long cnt = boardRepository.conutAll();   // 글 목록 전체의 개수
//        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지' ?

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
//        int startPage = 0;
//        int endPage = 0;
//
//        // 해당 페이지의 글 목록
//        List<Post> list = null;

//        if(cnt > 0){  // 데이터가 최소 1개 이상 있는 경우만 페이징
//            //  page 값 보정
//            if(page > totalPage) page = totalPage;
//
//            // 몇번째 데이터부터 fromRow
//            int fromRow = (page - 1) * pageRows;
//
//            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
//            startPage = (((page - 1) / writePages) * writePages) + 1;
//            endPage = startPage + writePages - 1;
//            if (endPage >= totalPage) endPage = totalPage;
//
//            // 해당페이지의 글 목록 읽어오기
//            list = boardRepository.selectFromRow(fromRow, pageRows);
//            model.addAttribute("list", list);
//        } else {
//            page = 0;
//        }

//        model.addAttribute("cnt", cnt);  // 전체 글 개수
//        model.addAttribute("page", page); // 현재 페이지
//        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
//        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수
//
//        // [페이징]
//        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
//        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
//        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
//        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

//        return list;
//    }
//    @Override
//    public List<Post> list(Integer page, Model model) {
//        return null;
//    }

//    @Override
//    public Post selectById(Long id) {
//        Post post = boardRepository.findById(id);
//
//        if(post != null){
//            // 첨부파일 정보 가져오기
//            List<Attachment> fileList = attachmentRepository.findByPost(post.getId());
//            setImage(fileList);   // 이미지 파일 여부 세팅
//            post.setFileList(fileList);

//        }
//
//        return post;
//    }

//    @Override
//    public int update(Post post
//            , Map<String, MultipartFile> files
//            , Long[] delfile) {
//        int result = boardRepository.update(post);

        // 새로운 첨부파일 추가
//        addFiles(files, post.getId());

        // 삭제할 첨부파일(들) 삭제
//        if(delfile != null){
//            for(Long fileId : delfile){
//                Attachment file = attachmentRepository.findById(fileId);
//                if(file != null){
//                    delFile(file);   // 물리적으로 파일 삭제
//                    attachmentRepository.delete(file);   // DB 에서 삭제
//                }
//            }
//        }

//        return result;
//    }
//    @Override
//    public int deleteById(Long id) {
//        int result = 0;
//        Post post = boardRepository.findById(id);  // 존재하는 데이터인지 읽어와보기
//        if(post != null){  // 존재한다면 삭제 진행.
//            //물리적으로 저장된 첨부파일(들) 삭제
//            List<Attachment> fileList = attachmentRepository.findByPost(id);
//            if(fileList != null){
//                for(Attachment file : fileList){
//                    delFile(file);
//                }
//            }
//            result = boardRepository.delete(post);
//        }
//        return result;
//    }
}
