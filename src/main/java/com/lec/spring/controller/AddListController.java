//package com.lec.spring.controller;
//
//import com.lec.spring.domain.TouristData;
//import com.lec.spring.service.AddListService;
//import com.lec.spring.service.TouristService;
//import com.lec.spring.util.U;
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class AddListController {
//
//
//
//    @Autowired
//    private  AddListService addListService;
//
//
//
//    @GetMapping("/addDestination")
//    public void addDestination(){}
//
//    @PostMapping("/addDestination")
//    public String addDestinationOk(
//            @RequestParam Map<String, MultipartFile> files
//             ,@Valid TouristData touristData
//             ,BindingResult result
//             ,Model model   // 매개변수 선언시 BindingResult 보다 Model 을 뒤에 두어야 한다.
//             ,RedirectAttributes redirectAttrs
////
//    ) {
//
//        HttpSession getSession = U.getSession();
//        if (result.hasErrors()) {
//            redirectAttrs.addFlashAttribute("title", touristData.getTitle());
//            redirectAttrs.addFlashAttribute("addr1", touristData.getAddr1());
//            redirectAttrs.addFlashAttribute("contenttypeid",touristData.getContenttypeid());
//            redirectAttrs.addFlashAttribute("mapx",touristData.getMapx());
//            redirectAttrs.addFlashAttribute("mapy",touristData.getMapy());
//            for (var err : result.getFieldErrors()) {
//                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
//            }
//
//            return "redirect:/addDestination";
//        }
//        // 서비스에 TouristData 전달하여 저장하기
//        ;
//
//        // 처리 결과를 모델에 추가
//        model.addAttribute("result", addListService.addDestination(touristData, files));
//
//        return "/addDestinationOk"; // 처리 결과를 보여주는 페이지
//    }
//
//
//    @GetMapping("list")
//    public void test1(){
//
//    }
//}
