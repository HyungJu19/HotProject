package com.lec.spring.controller;


import com.lec.spring.domain.User;
import com.lec.spring.domain.UserValidator;
import com.lec.spring.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public void login(Model model) {}

    @PostMapping("/login")
    public void loginProcess() {
        System.out.println("POST: /user/login 요청발생!");
    }


    // onAuthenticationFailure 에서 로그인 실패시 forwarding 용
    // request 에 담겨진 attribute 는 Thymeleaf 에서 그대로 표현 가능.
    @PostMapping("/loginError")
    public String loginError() {return "/user/login";}

    @GetMapping("/loginError")
    public String loginError2() {return "/user/login";}


    @RequestMapping("/rejectAuth")
    public String rejectAuth(){
        return "fragments/rejectAuth";
    }


    @GetMapping("/signup")
    public void signup() {}

    @PostMapping("/signup")
    public String signupOk(@Valid User user
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttrs
    ) {
        // 검증 에러가 있었다면 redirect 한다
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("username", user.getUsername());
            redirectAttrs.addFlashAttribute("password", user.getPassword());
            redirectAttrs.addFlashAttribute("nickname", user.getNickname());
            redirectAttrs.addFlashAttribute("email", user.getEmail());

            List<FieldError> errList = result.getFieldErrors();
            for (FieldError err : errList) {
                redirectAttrs.addFlashAttribute("error", err.getCode());  // 가장 처음에 발견된 에러를 담아 보낸다
                break;
            }


            return "redirect:/user/signup";
        }


        //에러 없으면 회원 등록 진행
        String page = "/user/signupOk";
        int cnt = userService.signup(user);
        model.addAttribute("result", cnt);
        return page;
    }



    // 아이디 찾기 폼
    @RequestMapping(value = "/findId")
    public String findId() throws Exception {
        return "/user/findId";
    }

    // 아이디 찾기
    @RequestMapping(value = "/findIdOk", method = RequestMethod.POST)
    public String findId(HttpServletResponse response, @RequestParam("email") String email, Model md) throws Exception {
        String find = userService.findId(response, email);
//        System.out.println(find);
        md.addAttribute("find", find);
        return "/user/findIdOk";
    }

    // 비밀번호 찾기 폼
    @RequestMapping(value = "/findpw")
    public String findpw() throws Exception {
        return "/user/findpw";
    }

    // 비밀번호 찾기
    @RequestMapping(value = "/findpwOk", method = RequestMethod.POST)
    public void findpw(@ModelAttribute User user, HttpServletResponse response) throws Exception{
        userService.findpw(response, user);
    }


    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(userValidator);
    }


}