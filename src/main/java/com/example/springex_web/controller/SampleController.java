package com.example.springex_web.controller;


import com.example.springex_web.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Log4j2

public class SampleController {
    @GetMapping("/hello")
    public void hello() { log.info("hello..............");}

    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1......");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA") String name,
                    @RequestParam(name = "age", defaultValue = "20")int age) {
        log.info("ex2.........");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex3")
    public void ex3(@RequestParam(name = "dueDate", defaultValue = "2026-03-24") LocalDate dueDate) {
//    public void ex3(@RequestParam("dueDate") LocalDate dueDate) {
        log.info("ex3..........");
        log.info("dueDate: " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4( Model model){
        log.info("-------");
        model.addAttribute("message","Hello World!");
    }
    @GetMapping("/ex4_1")
    public void ex4Extra(@ModelAttribute("dto") TodoDTO todoDTO , Model model){
        log.info(todoDTO);

    }

    @GetMapping("/ex5") // /ex5 요청이 들어오면 이 메서드 실행
    public String ex5(RedirectAttributes redirectAttributes){

        // addAttribute:
        // redirect 시 URL 뒤에 쿼리스트링으로 붙여서 전달함
        // 예: redirect:/ex6?name=ABC
        redirectAttributes.addAttribute("name","ABC");

        // addFlashAttribute:
        // redirect 할 때 1번만 잠깐 전달되는 데이터
        // URL에는 보이지 않고, 리다이렉트된 다음 요청에서만 사용할 수 있음
        // 보통 성공/실패 메시지 전달할 때 많이 씀
        redirectAttributes.addFlashAttribute("result","success");

        // 실제로는 /ex6 로 리다이렉트됨
        // 위 addAttribute 때문에 최종적으로는 /ex6?name=ABC 형태가 됨
        return "redirect:/ex6";
    }




    @GetMapping("/ex6")
        public void ex6(){

        }
    @GetMapping("/ex7")
    public void ex7(@RequestParam("p1") String p1, @RequestParam("p2") int p2) {
    log.info("p1");
    log.info("p2");
    }

}
