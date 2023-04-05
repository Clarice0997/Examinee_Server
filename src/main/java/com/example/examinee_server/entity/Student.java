package com.example.examinee_server.entity;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String name;
    private String studentId;
    private String password;
}
