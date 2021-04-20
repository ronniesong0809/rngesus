package com.ronsong.rngesus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ronsong.rngesus.model.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {
    List<Tag> insertTags(List<String> tags, boolean isNew);
    void removeByPostId(List<String> tags);
}