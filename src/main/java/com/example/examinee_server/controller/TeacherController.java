package com.example.examinee_server.controller;

import com.example.examinee_server.entity.Teacher;
import com.example.examinee_server.service.TeacherService;
import com.example.examinee_server.utils.Login;
import com.example.examinee_server.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/apis/teacher")
public class TeacherController {
    // 注入Service
    @Autowired
    private TeacherService teacherService;

    // 登录接口
    @PostMapping("/login")
    @ApiOperation(value = "教师登录")
    public Result login(Teacher teacher, HttpServletResponse response) {
        Login login = teacherService.login(teacher);
        if (login.getCode() == 200) {
            response.setStatus(login.getCode());
            return Result.ok(login.getCode()).data("token", login.getToken()).data("message", "登录成功");
        } else {
            response.setStatus(login.getCode());
            return Result.error(login.getCode()).data("message", login.getMessage());
        }
    }
}
