package com.ronsong.rngesus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ronsong.rngesus.model.dto.CreatePostDTO;
import com.ronsong.rngesus.model.dto.EditPostDTO;
import com.ronsong.rngesus.model.entity.Post;
import com.ronsong.rngesus.model.entity.User;
import com.ronsong.rngesus.model.vo.PostVO;

import java.util.Map;

public interface PostService extends IService<Post> {
    Page<PostVO> getAllPost(Page<PostVO> page, String tag);
    Post createPost(User user, CreatePostDTO createPostDTO);
    Post editPost(User user, EditPostDTO editPostDTO);
    Map<String, Object> getPostDetail(String id);
}