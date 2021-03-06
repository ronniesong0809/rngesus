package com.ronsong.rngesus.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ronsong.rngesus.common.exception.ApiAsserts;
import com.ronsong.rngesus.mapper.PostMapper;
import com.ronsong.rngesus.mapper.TagMapper;
import com.ronsong.rngesus.model.dto.CreatePostDTO;
import com.ronsong.rngesus.model.dto.EditPostDTO;
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
import java.util.*;
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
            List<Tag> tags = tagService.insertTags(createPostDTO.getTags(), true);
            postTagService.createPostTags(newPost.getId(), tags);
        }

        return newPost;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post editPost(User user, EditPostDTO editPostDTO) {
        Post post = this.baseMapper.selectById(editPostDTO.getId());
        post.setTitle(editPostDTO.getTitle());
        post.setContent(editPostDTO.getContent());
        post.setModifyTime(new Date());
        this.baseMapper.updateById(post);

        if (!ObjectUtils.isEmpty(editPostDTO.getTags())) {
            List<Tag> tags = tagService.insertTags(editPostDTO.getTags(), false);
            postTagService.createPostTags(post.getId(), tags);
        } else {
            List<String> tags = postTagService.deleteByPostId(post.getId());
            tagService.removeByPostId(tags);
        }

        return post;
    }

    @Override
    public Map<String, Object> getPostDetail(String id) {
        Map<String, Object> map = new HashMap<>();
        Post post = this.baseMapper.selectById(id);

        if (ObjectUtil.isNull(post)) {
            ApiAsserts.fail("post not found");
        }

        post.setView(post.getView() + 1);
        this.baseMapper.updateById(post);
        map.put("post", post);

        Set<String> set = new HashSet<>();
        for (PostTag postTag : postTagService.list(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, post.getId()))) {
            set.add(postTag.getTagId());
        }

        if (!set.isEmpty()) {
            List<Tag> tags = tagService.listByIds(set);
            map.put("tags", tags);
        }

        return map;
    }
}