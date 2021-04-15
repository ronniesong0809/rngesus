package com.ronsong.rngesus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ronsong.rngesus.model.dto.CreatePostDTO;
import com.ronsong.rngesus.model.entity.Post;
import com.ronsong.rngesus.model.entity.User;
import com.ronsong.rngesus.model.vo.PostVO;

public interface PostService extends IService<Post> {
    Page<PostVO> getAllPost(Page<PostVO> page, String tag);
    Post createPost(User user, CreatePostDTO createPostDTO);
}