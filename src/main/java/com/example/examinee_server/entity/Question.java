package com.example.examinee_server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("question")
public class Question {
    private Integer id;
    private String paperId;
    private String type;
    private String content;
    private Integer score;
    private String answer;
    private String options;
}
