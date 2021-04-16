package com.ronsong.rngesus.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@TableName("post_tag")
@Accessors(chain = true)
public class PostTag implements Serializable {
    private static final long serialVersionUID = 6559052437436894283L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("tag_id")
    private String tagId;

    @TableField("post_id")
    private String postId;
}