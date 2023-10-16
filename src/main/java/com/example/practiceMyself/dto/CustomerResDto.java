package com.example.practiceMyself.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor // 기본생성자 생성해주는 어노테이션
@AllArgsConstructor // 오버로딩된 생성자 생성
@Getter
@Setter
@ToString
@Builder
public class CustomerResDto {
    // 입력할 때는 null로 들어올까봐 dto를 입출력을 나눠서 작용
    // 출력 화면 dto
    private Long id;
    private String name;
    private String email;
    private int age;

    private LocalDateTime entryDate;
}
