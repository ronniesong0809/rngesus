package com.ronsong.rngesus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ronsong.rngesus.common.api.ApiResult;
import com.ronsong.rngesus.model.vo.PostVO;
import com.ronsong.rngesus.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController {
    @Resource
    private PostService postService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab, @RequestParam(value = "current", defaultValue = "1") Integer current, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<PostVO> postPage = postService.getList(new Page<>(current, size), tab);
        return ApiResult.success(postPage);
    }
}