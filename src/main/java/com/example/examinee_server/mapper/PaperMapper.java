package com.example.examinee_server.mapper;

import com.example.examinee_server.entity.Paper;
import com.example.examinee_server.entity.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperMapper {
    // 插入试卷
    @Insert("INSERT INTO paper(paper_id,paper_name) values (#{paperId},#{paperName})")
    public int insertInitPaper(Paper paper);

    // 批量插入题目
    @Insert("insert into question(paper_id,type,content,score,answer,options) values (#{paperId},#{type},#{content},#{score},#{answer},#{options})")
    public int insertQuestion(Question question);

    // 查询考卷
    @Select("select * from paper")
    public List<Paper> selectPapers();

    // 根据 ID 删除考卷
    @Delete("delete from paper where id = #{id}")
    public int deletePaperById(Integer id);
}
