package com.ronsong.rngesus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ronsong.rngesus.model.entity.PostTag;

import java.util.List;

public interface PostTagService extends IService<PostTag> {
    List<PostTag> selectByPostId(String topicId);
}