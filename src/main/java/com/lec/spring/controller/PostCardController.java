package com.lec.spring.controller;


import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.PostCardData;
import com.lec.spring.domain.User;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.service.PostCardService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


//광현

@Controller
@RequestMapping("/postCard")
public class PostCardController {

    @Autowired
    private PostCardService postCardService;

    private PrincipalDetails principalDetails;

    private UserRepository userRepository;

    @Autowired
    private ServletRequest request;

    private User user;



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
//        int cnt = postCardService.postCardDataSave(postCardData);
//
//        userRepository.findByUsername(user.getUsername()).getUid();
//
//        model.addAttribute("result", cnt);
//        return page;
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(HttpServletResponse response, @ModelAttribute PostCardData postCardData) throws Exception {

        principalDetails = new PrincipalDetails(user);
        Long uid = principalDetails.getUser().getUid();

        postCardData.setUid(uid);

        System.out.println(postCardData.getTravel_date());
        System.out.println(postCardData.getRegion());
        System.out.println(postCardData.getUid());

        postCardService.postCardDataSave(response, postCardData);
    }


}
