package com.example.springex_web.controller;

import com.example.springex_web.dto.TodoDTO;
import com.example.springex_web.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Todo 관련 요청을 처리하는 Controller
// 기본 URL : /todo
@Controller
@Log4j2
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

//    // GET /todo/list - Todo 목록 페이지
//    @GetMapping("/list")
//    public void list(Model model) {
//        log.info("todo list....");
//    }
        @GetMapping("/list")
        public void list(Model model){
            log.info("todo list......");
            model.addAttribute("dtoList",todoService.getAll());
        }

    // GET /todo/register - Todo 등록 페이지
    @GetMapping("/register")
    public void register() {
        log.info("todo register.....");
    }

    // POST /todo/register - Todo 등록 처리
    // @Valid : TodoDTO 유효성 검사 실행
    // BindingResult : 유효성 검사 결과 저장
    // RedirectAttributes : 리다이렉트 시 데이터 전달
    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("POST todo register.......");

        // 유효성 검사 실패 시 에러를 담아 등록 페이지로 리다이렉트
        if (bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);
        todoService.register(todoDTO);
        // 등록 성공 시 목록 페이지로 리다이렉트
        return "redirect:/todo/list";
    }



}
