package com.example.examinee_server.utils;

import com.example.examinee_server.entity.Paper;
import com.example.examinee_server.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class Bridge {
    private int code;
    private String message;
    private Student student;
    private List<Paper> papers;
}
