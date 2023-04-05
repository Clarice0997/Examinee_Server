package com.example.examinee_server.service;

import com.example.examinee_server.entity.Teacher;
import com.example.examinee_server.mapper.TeacherMapper;
import com.example.examinee_server.utils.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    // 注入 Mapper
    @Autowired
    private TeacherMapper teacherMapper;

    public Login login(Teacher teacher){
        // 构建登录对象
        Login login = new Login();
        // 判断账号是否为空
        if (null == teacher.getUsername() || "".equals(teacher.getUsername())) {
            login.setCode(400);
            login.setMessage("账号不能为空");
            return login;
        }

        // 判断密码是否为空
        if (null == teacher.getPassword() || "".equals(teacher.getPassword())) {
            login.setCode(400);
            login.setMessage("密码不能为空");
            return login;
        }

        // 预处理 去除空字符
        teacher.setUsername(teacher.getUsername().trim());
        teacher.setPassword(teacher.getPassword().trim());
        // 获取当前登录用户账号
        String username = teacher.getUsername();
        String password = teacher.getPassword();

        // 判断密码长度是否合法
        if (password.length() < 6 || password.length() > 20) {
            login.setCode(400);
            login.setMessage("密码长度应为6-20位");
            return login;
        }

        // 根据账号获取教师信息
        Teacher saveTeacher = teacherMapper.getTeacher(username);

        // 判断教师是否存在
        if(saveTeacher == null || saveTeacher.getUsername() == null){
            login.setCode(403);
            login.setMessage("账号密码错误");
            return login;
        }

        // 判断账号密码是否相同
        if (saveTeacher.getPassword().equals(password)) {
//            String token = JWTUtils.generateToken(account);
            String token = "fakeToken";
            login.setCode(200);
            login.setToken(token);
            return login;
        } else {
            login.setCode(403);
            login.setMessage("账号密码错误");
            return login;
        }
    }
}
