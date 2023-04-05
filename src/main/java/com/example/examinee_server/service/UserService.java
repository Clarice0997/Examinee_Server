package com.example.examinee_server.service;

import com.example.examinee_server.entity.Student;
import com.example.examinee_server.mapper.UserMapper;
import com.example.examinee_server.utils.Bridge;
import com.example.examinee_server.utils.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // 注入 Mapper
    @Autowired
    private UserMapper userMapper;
    // 注入 bcrypt
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录 Service
     * @param student
     * @return
     */
    public Login login(Student student){
        // 构建登录对象
        Login login = new Login();
        // 判断账号是否为空
        if (null == student.getStudentId() || "".equals(student.getStudentId())) {
            login.setCode(400);
            login.setMessage("学号不能为空");
            return login;
        }

        // 判断密码是否为空
        if (null == student.getPassword() || "".equals(student.getPassword())) {
            login.setCode(400);
            login.setMessage("密码不能为空");
            return login;
        }

        // 预处理 去除空字符
        student.setStudentId(student.getStudentId().trim());
        student.setPassword(student.getPassword().trim());
        // 获取当前登录用户账号
        String studentId = student.getStudentId();
        String password = student.getPassword();

        // 判断密码长度是否合法
        if (password.length() < 6 || password.length() > 20) {
            login.setCode(400);
            login.setMessage("密码长度应为6-20位");
            return login;
        }

        // 根据学号获取学生信息
        Student saveStudent = userMapper.getUser(student.getStudentId());
        // 判断学生是否存在
        if(saveStudent != null && saveStudent.getId() == null){
            System.out.println("用户不存在");
            login.setCode(404);
            login.setMessage("学号不存在");
            return login;
        }
        // 判断账号密码是否相同
        // 获取密码
        String savePassword = student.getPassword();
        String originPassword = saveStudent.getPassword();

        System.out.println(originPassword);
        System.out.println(savePassword);
        System.out.println(bCryptPasswordEncoder.matches(savePassword, originPassword));

        if (bCryptPasswordEncoder.matches(savePassword, originPassword)) {
//            String token = JWTUtils.generateToken(account);
            String token = "fakeToken";
            login.setCode(200);
            login.setToken(token);
            return login;
        } else {
            login.setCode(403);
            login.setMessage("学号密码错误");
            return login;
        }
    }

    /**
     * 注册 Service
     * @param student
     * @return
     */
    public Bridge register(Student student){
        // 构建通信对象
        Bridge bridge = new Bridge();
        // 判断学号是否为空
        if (null == student.getStudentId() || "".equals(student.getStudentId())) {
            bridge.setCode(400);
            bridge.setMessage("学号不能为空");
            return bridge;
        }

        // 判断密码是否为空
        if (null == student.getPassword() || "".equals(student.getPassword())) {
            bridge.setCode(400);
            bridge.setMessage("密码不能为空");
            return bridge;
        }

        // 预处理 去除空字符
        student.setStudentId(student.getStudentId().trim());
        student.setPassword(student.getPassword().trim());
        // 获取当前注册用户账号
        String studentId = student.getStudentId();
        String password = student.getPassword();


        // 判断密码长度是否合法
        if (password.length() < 6 || password.length() > 20) {
            bridge.setCode(400);
            bridge.setMessage("密码长度应为6-20位");
            return bridge;
        }

        // 判断账号是否存在
        if (userMapper.getUser(studentId) == null) {
            System.out.println("用户不存在");
        } else {
            System.out.println("用户已存在");
            bridge.setCode(409);
            bridge.setMessage("账号已存在");
            return bridge;
        }

        // 重新赋值加密密码
        student.setPassword(bCryptPasswordEncoder.encode(password));

        // 判断是否注册成功
        if (userMapper.register(student) != 0) {
            bridge.setCode(200);
            bridge.setMessage("注册成功");
        } else {
            bridge.setCode(400);
            bridge.setMessage("注册失败");
        }
        return bridge;
    }
}
