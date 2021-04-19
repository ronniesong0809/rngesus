package com.ronsong.rngesus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ronsong.rngesus.common.api.ApiResult;
import com.ronsong.rngesus.model.dto.CreatePostDTO;
import com.ronsong.rngesus.model.dto.EditPostDTO;
import com.ronsong.rngesus.model.entity.Post;
import com.ronsong.rngesus.model.entity.User;
import com.ronsong.rngesus.model.vo.PostVO;
import com.ronsong.rngesus.service.PostService;
import com.ronsong.rngesus.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

import static com.ronsong.rngesus.common.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController {
    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab, @RequestParam(value = "current", defaultValue = "1") Integer current, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<PostVO> postPage = postService.getAllPost(new Page<>(current, size), tab);
        return ApiResult.success(postPage);
    }

    @PostMapping("/create")
    public ApiResult<Post> create(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody CreatePostDTO createPostDTO) {
        User user = userService.getUserByUserName(userName);
        Post post = postService.createPost(user, createPostDTO);
        return ApiResult.success(post);
    }

    @PostMapping("/edit")
    public ApiResult<Post> edit(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody EditPostDTO editPostDTO) {
        User user = userService.getUserByUserName(userName);
        Post post = postService.editPost(user, editPostDTO);
        return ApiResult.success(post);
    }

    @GetMapping("/detail")
    public ApiResult<Map<String, Object>> detail(@RequestParam("id") String id) {
        Map<String, Object> post = postService.getPostDetail(id);
        return ApiResult.success(post);
    }
}