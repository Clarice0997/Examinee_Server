package com.example.examinee_server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("examinee")
public class Student {
    private Integer id;
    private String name;
    private String studentId;
    private String password;
}
