package com.ronsong.rngesus.model.vo;

import com.ronsong.rngesus.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostVO implements Serializable {
    private static final long serialVersionUID = 8260353099469691758L;
    private String id;
    private String title;
    private String userId;
    private String userName;
    private String avatar;
    private String alias;
    private List<Tag> tags;
    private Integer comments;
    private Integer collects;
    private Integer view;
    private Date createTime;
    private Date modifyTime;
}