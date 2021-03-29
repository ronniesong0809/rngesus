package com.ronsong.rngesus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ronsong.rngesus.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
