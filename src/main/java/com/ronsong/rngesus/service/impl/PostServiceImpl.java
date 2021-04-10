package com.ronsong.rngesus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ronsong.rngesus.mapper.TagMapper;
import com.ronsong.rngesus.mapper.PostMapper;
import com.ronsong.rngesus.model.entity.Post;
import com.ronsong.rngesus.model.entity.Tag;
import com.ronsong.rngesus.model.entity.PostTag;
import com.ronsong.rngesus.model.vo.PostVO;
import com.ronsong.rngesus.service.PostService;
import com.ronsong.rngesus.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Resource
    private TagMapper tagMapper;

    @Autowired
    private PostTagService postTagService;

    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        Page<PostVO> found = this.baseMapper.selectListAndPage(page, tab);

        found.getRecords().forEach(post -> {
            List<PostTag> postTags = postTagService.selectByPostId(post.getId());

            if (!postTags.isEmpty()) {
                List<String> tagIds = postTags.stream().map(PostTag::getTagId).collect(Collectors.toList());
                List<Tag> tags = tagMapper.selectBatchIds(tagIds);
                post.setTags(tags);
            }
        });
        return found;
    }
}