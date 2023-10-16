package com.example.practiceMyself.dto;

import lombok.*;

import javax.xml.transform.sax.SAXResult;

@NoArgsConstructor // 기본생성자 생성해주는 어노테이션
@AllArgsConstructor // 오버로딩된 생성자 생성
@Getter
@Setter
@ToString
@Builder
public class CustomerReqDto {
    // 입력화면 dto
    private String name;
    private String email;
    private int age;
}
