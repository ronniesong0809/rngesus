package com.ronsong.rngesus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ronsong.rngesus.model.entity.Post;
import com.ronsong.rngesus.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper extends BaseMapper<Post> {
    @Select("<script>" +
            "SELECT p.id, p.title, p.user_id, p.comments, p.view, p.create_time, p.modify_time, u.username\n" +
            "FROM post p LEFT JOIN users u ON p.user_id = u.id" +
            "<where></where>" +
            "ORDER BY p.create_time DESC" +
            "</script>")
    Page<PostVO> selectListAndPage(@Param("page") Page<PostVO> page, @Param("tab") String tab);
}
