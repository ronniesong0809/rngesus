package com.ronsong.rngesus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ronsong.rngesus.model.entity.Post;
import com.ronsong.rngesus.model.vo.PostVO;

public interface PostService extends IService<Post> {
    Page<PostVO> getList(Page<PostVO> page, String tag);
}
