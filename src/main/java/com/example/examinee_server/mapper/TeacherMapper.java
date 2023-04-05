package com.example.examinee_server.mapper;

import com.example.examinee_server.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {
    @Select("select * from examiner where username = #{username}")
    public Teacher getTeacher(String username);
}
