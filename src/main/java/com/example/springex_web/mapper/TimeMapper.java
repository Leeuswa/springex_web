package com.example.springex_web.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    @Select("select now()") // ;(세미콜론)은 사용하지 않는다.
    String getTime();
}
