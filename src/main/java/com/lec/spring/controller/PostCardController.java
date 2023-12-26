package com.lec.spring.controller;


import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.PostCardData;
import com.lec.spring.domain.User;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.service.PostCardService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//광현

@Controller
@RequestMapping("/postCard")
public class PostCardController {

    @Autowired
    private PostCardService postCardService;

    private UserRepository userRepository;




//    @GetMapping("/main")
//    public void main(){}

    @RequestMapping(value ="/main")
    public String main(){
        return "/postCard/main";
    }

//    @PostMapping("/main")
//    public String saveOk(@Valid PostCardData postCardData
//            , BindingResult result
//            , Model model
//            , User user
//    ) {
//
//        String page = "/postCard/saveOk";
//
//        Long uid = userRepository.findByUsername(user.getUsername()).getUid();
//
//        postCardData.setUid(uid);
//
//        int cnt = postCardService.postCardDataSave(postCardData);
//
//        model.addAttribute("result", cnt);
//        return page;
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(HttpServletResponse response, @ModelAttribute PostCardData postCardData, Authentication authentication) throws Exception {

        PrincipalDetails principalDetails1 = (PrincipalDetails) authentication.getPrincipal();
        Long uid = principalDetails1.getUser().getUid();

        postCardData.setUid(uid);

        postCardService.postCardDataSave(response, postCardData);
    }


}
