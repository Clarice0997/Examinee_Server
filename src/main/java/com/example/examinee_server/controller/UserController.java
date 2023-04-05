package com.example.examinee_server.controller;

import com.example.examinee_server.entity.Student;
import com.example.examinee_server.service.UserService;
import com.example.examinee_server.utils.Bridge;
import com.example.examinee_server.utils.Login;
import com.example.examinee_server.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/apis/user")
public class UserController {
    // 注入Service
    @Autowired
    private UserService userService;

    // 登录接口
    @PostMapping("/login")
    @ApiOperation(value = "学生登录")
    public Result login(Student student, HttpServletResponse response) {
        Login login = userService.login(student);
        if (login.getCode() == 200) {
            response.setStatus(login.getCode());
            return Result.ok(login.getCode()).data("token", login.getToken()).data("message", "登录成功");
        } else {
            response.setStatus(login.getCode());
            return Result.error(login.getCode()).data("message", login.getMessage());
        }
    }

    // 注册接口
    @PostMapping("/register")
    @ApiOperation(value = "学生注册")
    public Result register(Student student, HttpServletResponse response) {
        Bridge bridge = userService.register(student);
        if (bridge.getCode() == 200) {
            response.setStatus(bridge.getCode());
            return Result.ok(bridge.getCode()).data("message", bridge.getMessage());
        } else {
            response.setStatus(bridge.getCode());
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }
}
