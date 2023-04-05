package com.example.examinee_server.service;

import com.example.examinee_server.entity.Paper;
import com.example.examinee_server.entity.Question;
import com.example.examinee_server.mapper.PaperMapper;
import com.example.examinee_server.utils.Bridge;
import com.example.examinee_server.utils.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {
    // 注入Mapper
    @Autowired
    private PaperMapper paperMapper;

    public Bridge initPaper(Paper paper) {
        Bridge bridge = new Bridge();
        // 生成试卷 ID
        String uuid = Uuid.generateUuid();
        // 插入试卷 ID
        paper.setPaperId(uuid);
        List<Question> questionList = paper.getQuestionList();
        questionList.forEach(question -> question.setPaperId(uuid));
        // 插入试卷表
        paperMapper.insertInitPaper(paper);
        // 插入题目表
        questionList.forEach(question -> paperMapper.insertQuestion(question));
        // 返回成功请求
        bridge.setCode(200);
        bridge.setMessage(uuid);
        return bridge;
    }

    public Bridge getPapers() {
        Bridge bridge = new Bridge();
        // 获取考卷列表
        List<Paper> papers = paperMapper.selectPapers();
        // 返回成功请求
        bridge.setCode(200);
        bridge.setPapers(papers);
        return bridge;
    }

    public Bridge delPaper(Integer id) {
        Bridge bridge = new Bridge();
        // 获取考卷列表
        paperMapper.deletePaperById(id);
        // 返回成功请求
        bridge.setCode(200);
        bridge.setMessage("删除试卷成功");
        return bridge;
    }
}
