package com.lec.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {


//캠핑
    @GetMapping("/camping/list")
    public void compingList(){}

    @GetMapping("/camping/detail/{id}")
    public void campingDetail() {
    }

    @GetMapping("/camping/write")
    public void campingWrite(){}
    @PostMapping("/camping/write")
    public String campingWriteOk(){
        return "board/camping/writeOk";
    }

    @GetMapping("/camping/update")
    public void campingUpdate(){}

    @PostMapping("/camping/update")
    public String campingUpdateOk(){
        return "board/camping/updateOk";
    }

    @PostMapping("/camping/delete")
    public String campingdeleteOk(){
        return "board/camping/deleteOk";
    }



//유아동반
    @GetMapping("/family/list")
    public void familyList(){}

    @GetMapping("/family/detail/{id}")
    public String familyDetail(@PathVariable Long id, Model model) {
        return "board/family/detail";
    }

    @GetMapping("/family/write")
    public void familyWrite(){}
    @PostMapping("/family/write")
    public String familyWriteOk(){
        return "board/family/writeOk";
    }

    @GetMapping("/family/update")
    public void familyUpdate(){}

    @PostMapping("/family/update")
    public String familyUpdateOk(){
        return "board/family/updateOk";
    }

    @PostMapping("/family/delete")
    public String familyDeleteOk(){
        return "board/family/deleteOk";
    }



    //맛집

    @GetMapping("/foodie/list")
    public void foodieList(){}

    @GetMapping("/foodie/detail/{id}")
    public String foodieDetail(@PathVariable Long id, Model model) {
        return "board/foodie/detail";
    }

    @GetMapping("/foodie/write")
    public void foodieWrite(){}
    @PostMapping("/foodie/write")
    public String foodieWriteOk(){
        return "board/foodie/writeOk";
    }

    @GetMapping("/foodie/update")
    public void foodieUpdate(){}

    @PostMapping("/foodie/update")
    public String foodieUpdateOk(){
        return "board/foodie/updateOk";
    }

    @PostMapping("/foodie/delete")
    public String foodieDeleteOk(){
        return "board/foodie/deleteOk";
    }



    //맛집

    @GetMapping("/festival/list")
    public void festivalList(){}

    @GetMapping("/festival/detail/{id}")
    public String festivalDetail(@PathVariable Long id, Model model) {
        return "board/festival/detail";
    }

    @GetMapping("/festival/write")
    public void festivalWrite(){}
    @PostMapping("/festival/write")
    public String festivalWriteOk(){
        return "board/festival/writeOk";
    }

    @GetMapping("/festival/update")
    public void festivalUpdate(){}

    @PostMapping("/festival/update")
    public String festivalUpdateOk(){
        return "board/festival/updateOk";
    }

    @PostMapping("/festival/delete")
    public String festivalDeleteOk(){
        return "board/festival/deleteOk";
    }


    //스케줄

    @GetMapping("/schedule/list")
    public void scheduleList(){}

    @GetMapping("/schedule/detail/{id}")
    public String scheduleDetail(@PathVariable Long id, Model model) {
        return "board/schedule/detail";
    }
    @GetMapping("/schedule/write")
    public void scheduleWrite(){}
    @PostMapping("/schedule/write")
    public String scheduleWriteOk(){
        return "board/schedule/writeOk";
    }

    @GetMapping("/schedule/update")
    public void scheduleUpdate(){}

    @PostMapping("/schedule/update")
    public String scheduleUpdateOk(){
        return "board/schedule/updateOk";
    }

    @PostMapping("/schedule/delete")
    public String scheduleDeleteOk(){
        return "board/schedule/deleteOk";
    }
}

