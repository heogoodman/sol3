package com.example.practiceMyself.entity;

import jakarta.persistence.*;
import lombok.CustomLog;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "customer")
@DynamicUpdate // 작성한 내용만 업데이트 되도록 해주는 어노테이션
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime entryDate = LocalDateTime.now();
}
