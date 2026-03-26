package com.example.springex_web.service;

import com.example.springex_web.domain.TodoVO;
import com.example.springex_web.dto.TodoDTO;
import com.example.springex_web.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service                    // 스프링이 서비스 클래스로 인식
@Log4j2                     // log.info() 같은 로그 기능 사용 가능
@RequiredArgsConstructor    // final 필드 생성자 자동 생성 (Lombok)
public class TodoServiceimpl implements TodoService {

    private final TodoMapper todoMapper;    // DB 접근 담당 (MyBatis)
    private final ModelMapper modelMapper;  // 객체 변환 담당 (DTO ↔ VO)

    @Override
    public void register(TodoDTO todoDTO) {

        log.info(modelMapper);  // modelMapper 객체 로그 출력 (확인용)

        // 화면에서 받은 DTO를 DB에 저장할 VO로 변환
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        log.info(todoVO);  // 변환된 VO 로그 출력 (확인용)

        todoMapper.insert(todoVO);  // DB에 Todo 데이터 저장
    }

    @Override
   public List<TodoDTO> getAll() {
        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo,TodoDTO.class))
                .collect(Collectors.toList());
        return  dtoList;
    }

}
