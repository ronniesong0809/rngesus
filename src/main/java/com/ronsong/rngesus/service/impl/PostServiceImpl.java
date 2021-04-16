package com.ronsong.rngesus.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ronsong.rngesus.common.exception.ApiAsserts;
import com.ronsong.rngesus.mapper.PostMapper;
import com.ronsong.rngesus.mapper.TagMapper;
import com.ronsong.rngesus.model.dto.CreatePostDTO;
import com.ronsong.rngesus.model.entity.Post;
import com.ronsong.rngesus.model.entity.PostTag;
import com.ronsong.rngesus.model.entity.Tag;
import com.ronsong.rngesus.model.entity.User;
import com.ronsong.rngesus.model.vo.PostVO;
import com.ronsong.rngesus.service.PostService;
import com.ronsong.rngesus.service.PostTagService;
import com.ronsong.rngesus.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Resource
    private TagMapper tagMapper;

    @Autowired
    private PostTagService postTagService;

    @Autowired
    private TagService tagService;

    @Override
    public Page<PostVO> getAllPost(Page<PostVO> page, String tab) {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post createPost(User user, CreatePostDTO createPostDTO) {
        Post post = this.baseMapper.selectOne(new LambdaQueryWrapper<Post>().eq(Post::getTitle, createPostDTO.getTitle()));

        if (!ObjectUtil.isNull(post)) {
            ApiAsserts.fail("title already exists");
        }

        Post newPost = Post.builder()
                .userId(user.getId())
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(newPost);

        if (!ObjectUtils.isEmpty(createPostDTO.getTags())) {
            List<Tag> tags = tagService.insertTags(createPostDTO.getTags());
            postTagService.createPostTags(newPost.getId(), tags);
        }

        return newPost;
    }
}