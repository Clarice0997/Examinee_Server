package com.example.examinee_server.controller;

import com.example.examinee_server.entity.Paper;
import com.example.examinee_server.service.PaperService;
import com.example.examinee_server.utils.Bridge;
import com.example.examinee_server.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/apis/paper")
public class PaperController {
    // 注入 Service
    @Autowired
    private PaperService paperService;

    @ApiOperation(value = "插入考卷")
    @PostMapping("/")
    public Result initPaper(@RequestBody Paper paper, HttpServletResponse response) {
        Bridge bridge = paperService.initPaper(paper);
        if (bridge.getCode() == 200) {
            response.setStatus(bridge.getCode());
            return Result.ok(bridge.getCode()).data("paperId", bridge.getMessage());
        } else {
            response.setStatus(bridge.getCode());
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    @ApiOperation(value = "查询所有考卷")
    @GetMapping("/")
    public Result getPapers(HttpServletResponse response) {
        Bridge bridge = paperService.getPapers();
        if (bridge.getCode() == 200) {
            response.setStatus(bridge.getCode());
            return Result.ok(bridge.getCode()).data("papers", bridge.getPapers());
        } else {
            response.setStatus(bridge.getCode());
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }

    @ApiOperation(value = "删除考卷")
    @DeleteMapping("/{id}")
    public Result delPaper(@PathVariable Integer id, HttpServletResponse response) {
        Bridge bridge = paperService.delPaper(id);
        if (bridge.getCode() == 200) {
            response.setStatus(bridge.getCode());
            return Result.ok(bridge.getCode()).data("message", bridge.getMessage());
        } else {
            response.setStatus(bridge.getCode());
            return Result.error(bridge.getCode()).data("message", bridge.getMessage());
        }
    }
}
