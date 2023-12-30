package com.lec.spring.controller;


import com.lec.spring.domain.CampingData;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.PostValidator;
import com.lec.spring.domain.TouristData;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.TouristService;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private TouristService touristService;
    //승원 전체게시판
    @Autowired
    private BoardService boardService;

    public BoardController() {
        System.out.println("BoardController() 생성");
    }

    @GetMapping("/write")
    public void write( ) {
    }

    @PostMapping("/write")
    public String writeOk(
            @RequestParam Map<String, MultipartFile> files   // 첨부 파일
            , @Valid Post post
            , BindingResult result
            , Model model   // 매개변수 선언시 BindingResult 보다 Model 을 뒤에 두어야 한다.
            , RedirectAttributes redirectAttrs
    ) {
        // validation 에러가 있었다면 redirect 할거다!
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("title", post.getSubject());
            redirectAttrs.addFlashAttribute("content", post.getContent());

            for (var err : result.getFieldErrors()) {
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/write";
        }
        model.addAttribute("result", boardService.write(post, files));
        return "board/writeOk";

    }

    @GetMapping("/detail/{postId}")
    public String detail(@PathVariable Long postId, Model model) {
        model.addAttribute("post", boardService.detail(postId));
        return "board/detail";
    }

    // 페이징 사용
    @GetMapping("/list")
    public void list(HttpServletRequest request,String category,Integer page,Model model) {

        List<Post> list = boardService.list();

        model.addAttribute("list", list);
        model.addAttribute("category",category);
//        String category = request.getParameter("category");
        boardService.list(category,page, model);



    }
    // 페이징 사용

    @GetMapping("/update/{postId}")
    public String update(@PathVariable Long postId, Model model) {
        Post post = boardService.selectById(postId);
        model.addAttribute("post", post);
        return "board/update";
    }

    @PostMapping("/update")
    public String updateOk(
            @RequestParam Map<String, MultipartFile> files  // 새로 추가될 첨부파일들
            , Long[] delfile    // 삭제될 파일들
            , @Valid Post post
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttrs
    ) {
        if (result.hasErrors()) {

            redirectAttrs.addFlashAttribute("subject", post.getSubject());
            redirectAttrs.addFlashAttribute("content", post.getContent());

            for (var err : result.getFieldErrors()) {
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/update/" + post.getPostId();
        }


        model.addAttribute("result", boardService.update(post, files, delfile));
        return "board/updateOk";
    }

    @PostMapping("/delete")
    public String deleteOk(@RequestParam("postId") Long postId, Model model) {
        int result = boardService.deleteById(postId);
        model.addAttribute("result", result);
        return "board/deleteOk";
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("initBinder() 호출");
        binder.setValidator(new PostValidator());
    }

    // 페이징
    // pageRows 변경시 동작
    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows) {
        U.getSession().setAttribute("pageRows", pageRows);
        return "redirect:/board/list?page=" + page;
    }

    //승원 전체게시판
    //캠핑
    @GetMapping("/campingList")
    public void compingList() {
    }

    @GetMapping("/campingList/detail/{id}")
    public void campingDetail() {
    }




    @GetMapping("/touristList")
    public void familyList() {
    }

    @GetMapping("/familyList/detail/{id}")
    public String familyDetail(@PathVariable Long id, Model model) {
        return "board/family/detail";
    }



    @GetMapping("addDestination/search/{category}/{keyword}")
    @ResponseBody
    public List<TouristData> search(@PathVariable String category, @PathVariable String keyword, Model model) {
        List<TouristData> searchList = touristService.search(category, keyword);

        model.addAttribute("searchList", searchList);

        return searchList;
    }

    @GetMapping("camping/search/{keyword}")
    @ResponseBody
    public List<CampingData> searchCamping(@PathVariable String category, @PathVariable String keyword, Model model) {
        List<CampingData> searchcampingList = touristService.searchCamping(category, keyword);
        model.addAttribute("searchList", searchcampingList);

        return searchcampingList;
    }

}