package com.example.examinee_server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("paper")
public class Paper {
    private Integer id;
    private String paperId;
    private String paperName;
    private Date startTime;
    private Date endTime;
    private Integer examineeNumber;
    private String status;
    private List<Question> questionList;
}
