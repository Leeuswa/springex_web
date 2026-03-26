package com.example.springex_web.mapper;

import com.example.springex_web.domain.TodoVO;

public interface TodoMapper {
    String getTime();
    void insert(TodoVO todoVO);

}
