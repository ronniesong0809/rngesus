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
            "SELECT p.id, p.title, p.comments, p.view, p.create_time, p.modify_time, u.id, u.username, u.alias, u.avatar\n" +
            "FROM post p LEFT JOIN users u ON p.user_id = u.id" +
            "<where>" +
            "<if test=\"tab == 'hottest'\">"+
            "date(p.create_time) &gt;= NOW() - INTERVAL '1 month'" +
            "</if>" +
            "</where>" +
            "<if test=\"tab == 'hottest'\">"+
            "ORDER BY p.view desc, p.create_time DESC" +
            "</if>" +
            "<if test=\"tab != 'hottest'\">"+
            "ORDER BY p.create_time DESC" +
            "</if>" +
            "</script>")
    Page<PostVO> selectListAndPage(@Param("page") Page<PostVO> page, @Param("tab") String tab);
}