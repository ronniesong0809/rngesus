package com.ronsong.rngesus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ronsong.rngesus.mapper.PostTagMapper;
import com.ronsong.rngesus.model.entity.PostTag;
import com.ronsong.rngesus.model.entity.Tag;
import com.ronsong.rngesus.service.PostTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements PostTagService {

    @Override
    public List<PostTag> selectByPostId(String postId) {
        return this.baseMapper.selectList(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, postId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPostTags(String id, List<Tag> tags) {
        this.baseMapper.delete(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, id));

        tags.forEach(tag -> {
            PostTag postTag = PostTag.builder()
                    .postId(id)
                    .tagId(tag.getId())
                    .build();
            this.baseMapper.insert(postTag);
        });
    }
}