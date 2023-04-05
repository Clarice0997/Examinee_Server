package com.example.examinee_server.mapper;

import com.example.examinee_server.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    // 查询账号是否存在 Mapper
    @Select("select * from examinee where student_id = #{studentId}")
    public Student getUser(String studentId);

    // 注册 Mapper
    @Insert("insert into examinee values(null,#{name},#{studentId},#{password})")
    public int register(Student student);
}
