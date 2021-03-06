package com.ronsong.rngesus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ronsong.rngesus.mapper.TagMapper;
import com.ronsong.rngesus.model.entity.Tag;
import com.ronsong.rngesus.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> insertTags(List<String> tags, boolean isNew) {
        List<Tag> list = new ArrayList<>();
        for (String t : tags) {
            Tag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName, t));

            if (tag == null) {
                tag = Tag.builder()
                        .name(t)
                        .postCount(1)
                        .build();
                this.baseMapper.insert(tag);
            } else {
                if (isNew) {
                    tag.setPostCount(tag.getPostCount() + 1);
                }
                this.baseMapper.updateById(tag);
            }
            list.add(tag);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPostId(List<String> tags) {
        for (String t : tags) {
            Tag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getId, t));
            if (tag.getPostCount() > 1) {
                tag.setPostCount(tag.getPostCount() - 1);
                this.baseMapper.updateById(tag);
            } else {
                this.baseMapper.deleteById(tag);
            }
        }
    }
}