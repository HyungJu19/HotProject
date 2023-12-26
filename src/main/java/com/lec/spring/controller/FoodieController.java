package com.lec.spring.controller;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.TouristData;
import com.lec.spring.repository.TouristRepository;
import com.lec.spring.service.TouristService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//기원
@Controller
@RequestMapping("/theme/foodie/main")
public class FoodieController {


    @Autowired
    private TouristService touristservice;


    @GetMapping()
    public String local(Model model, HttpServletRequest request) {

        String areacode = request.getParameter("areacode");
        String sigungucode = request.getParameter("sigungucode");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int limit = 4;
        int offset = (page - 1) * limit;
        List<TouristData> dataList = touristservice.localfoodie(areacode, sigungucode, limit, offset);
        model.addAttribute("dataList", dataList);
        System.out.println(touristservice.localfoodie(areacode, sigungucode, limit, offset));
        System.out.println("data Lists =" + dataList);
        System.out.println("areacode =" + areacode);
        System.out.println("sigungucode =" + sigungucode);
        System.out.println("limit =" + limit);
        System.out.println("offset =" + offset);

        String category = "맛집";
        String visibility = "PUBLIC";
        List<Post> postList = touristservice.postList(category, visibility);
        model.addAttribute("postList", postList);
        System.out.println("postList =" + postList);

        return "theme/foodie/main";
    }


}