package com.ronsong.rngesus.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("post")
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    private static final long serialVersionUID = 2219791355529225913L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("user_id")
    private String userId;

    @NotBlank(message = "title is required")
    @TableField(value = "title")
    private String title;

    @NotBlank(message = "content is required")
    @TableField("content")
    private String content;

    @TableField("comments")
    @Builder.Default
    private Integer comments = 0;

    @TableField("view")
    @Builder.Default
    private Integer view = 0;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;
}
