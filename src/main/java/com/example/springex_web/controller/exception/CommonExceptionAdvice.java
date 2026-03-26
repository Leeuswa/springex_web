package com.example.springex_web.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice
@Log4j2
// 모든 컨트롤러에서 발생하는 예외를 공통으로 처리하는 클래스
public class CommonExceptionAdvice {

    @ResponseBody
    // 리턴값을 뷰 이름으로 해석하지 않고, 그대로 응답 본문(body)에 출력함
    @ExceptionHandler(NumberFormatException.class)
    // NumberFormatException 예외가 발생하면 이 메서드가 처리함
    public String exceptNumber(NumberFormatException numberFormatException) {

        // 구분선 로그 출력
        log.error("-----------------------------------");

        // 발생한 예외 메시지 출력
        // 예: For input string: "BBB"
        log.error(numberFormatException.getMessage());

        // 브라우저에 문자열 그대로 응답
        return "NUMBER FORMAT EXCEPTION";
    }

    @ResponseBody
    // 리턴값을 HTML 뷰 이름이 아니라 응답 데이터로 보냄
    @ExceptionHandler(Exception.class)
    // Exception은 모든 예외의 부모 클래스이므로, 대부분의 예외를 처리할 수 있음
    public String exceptCommon(Exception exception) {

        // 구분선 로그 출력
        log.error("-----------------------------------");

        // 예외 메시지 로그 출력
        log.error(exception.getMessage());

        // 예외 내용을 HTML 목록 형태로 만들기 위한 문자열 버퍼 생성
        StringBuffer buffer = new StringBuffer("<ul>");

        // 첫 번째 목록에 예외 메시지 추가
        buffer.append("<li>" + exception.getMessage() + "</li>");

        // 스택트레이스의 각 줄을 <li> 태그로 추가
        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });

        // ul 태그 닫기
        buffer.append("</ul>");

        // 완성된 HTML 문자열을 브라우저에 응답
        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    // NoHandlerFoundException 발생 시 이 메서드가 처리함
    @ResponseStatus(HttpStatus.NOT_FOUND)
    // 응답 상태 코드를 404 NOT FOUND로 지정
    public String notFound() {

        // /WEB-INF/views/custom404.jsp 페이지를 보여줌
        return "custom404";
    }
}
