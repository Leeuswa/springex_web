package com.example.springex_web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default   //기본값 세팅
    @Min(value = 1)
    @Positive  // 무조건 양수값을 가져야함 ,음수 x
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private  int size = 10;

    private String link;
    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;

    public int getSkip(){
        return (page -1) * 10;
    }

    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);
        if(finished){
            builder.append("&finished=on");
        }
        if(types != null && types.length > 0){
            for (int i = 0; i < types.length ; i++) {
                builder.append("&types=" + types[i]);
            }
        }
        if(keyword != null){
            try {
                builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(from != null){
            builder.append("&from=" + from.toString());
        }
        if(to != null){
            builder.append("&to=" + to.toString());
        }
        return builder.toString();
    }

    public boolean checkType(String type){
        if(types == null || types.length == 0){
            return false;
        }
        return Arrays.stream(types).allMatch(type::equals); //타입 equals와 일치하는게 하나라도 있으면 true를 반환
    }
}
