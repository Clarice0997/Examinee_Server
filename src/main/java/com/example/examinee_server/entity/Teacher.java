package com.example.examinee_server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("examiner")
public class Teacher {
    private Integer id;
    private String username;
    private String password;
}
