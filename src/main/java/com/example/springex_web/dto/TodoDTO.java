package com.example.springex_web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data           // getter, setter, toString, equals, hashCode 자동 생성 (Lombok)
@Builder        // 빌더 패턴 사용 가능 (Lombok) ex) TodoDTO.builder().title("제목").build()
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동 생성 (Lombok)
@NoArgsConstructor  // 기본 생성자 자동 생성 (Lombok)
public class TodoDTO {

    private Long tno;           // Todo 고유 번호 (PK)

    @NotEmpty                   // 빈 값 허용 안함 (유효성 검사)
    private String title;       // Todo 제목

    //미래의 작업시간이 현재나 과거를 갈 수가 없어서 Future사용
    @Future                     // 현재보다 미래 날짜만 허용 (유효성 검사)
    private LocalDate dueDate;  // Todo 마감일

    private boolean finished;   // Todo 완료 여부 (true: 완료, false: 미완료)

    @NotEmpty                   // 빈 값 허용 안함 (유효성 검사)
    private String writer;      // Todo 작성자

}
