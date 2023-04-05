package com.example.examinee_server.utils;

import com.example.examinee_server.entity.Student;
import lombok.Data;

@Data
public class Bridge {
    private int code;
    private String message;
    private Student student;
}
