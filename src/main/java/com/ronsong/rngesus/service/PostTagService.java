package com.ronsong.rngesus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ronsong.rngesus.model.entity.PostTag;
import com.ronsong.rngesus.model.entity.Tag;

import java.util.List;

public interface PostTagService extends IService<PostTag> {
    List<PostTag> selectByPostId(String topicId);
    void createPostTags(String id, List<Tag> tags);
    List<String> deleteByPostId(String id);
}