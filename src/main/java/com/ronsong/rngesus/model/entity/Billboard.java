package com.ronsong.rngesus.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("billboard")
public class Billboard implements Serializable {
    private static final long serialVersionUID = -1342981060726414530L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "content")
    private String content;

    @TableField(value = "timestamp", fill = FieldFill.INSERT)
    private Date timestamp;

    @Builder.Default
    @TableField("`show`")
    private boolean show = false;
}