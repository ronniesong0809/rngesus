package com.ronsong.rngesus.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ronsong.rngesus.common.exception.ApiAsserts;
import com.ronsong.rngesus.common.jwt.JwtUtil;
import com.ronsong.rngesus.mapper.UserMapper;
import com.ronsong.rngesus.model.dto.LoginDTO;
import com.ronsong.rngesus.model.dto.SignupDTO;
import com.ronsong.rngesus.model.entity.User;
import com.ronsong.rngesus.service.UserService;
import com.ronsong.rngesus.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Locale;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User executeSignup(SignupDTO dto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(User::getUsername, dto.getUsername()).or().eq(User::getEmail, dto.getEmail());
        User umsUser = baseMapper.selectOne(wrapper);

        if (!ObjectUtil.isEmpty(umsUser)) {
            ApiAsserts.fail("username or email already exists");
        }

        User newUser = User.builder()
                .username(dto.getUsername())
                .alias(dto.getUsername())
                .email(dto.getEmail())
                .password(MD5Util.md5Hex(dto.getPassword()))
                .avatar(MD5Util.md5Hex(dto.getEmail().toLowerCase(Locale.ENGLISH)))
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(newUser);
        return newUser;
    }

    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

            wrapper.eq(User::getUsername, dto.getUsername());
            User user = baseMapper.selectOne(wrapper);

            String encode = MD5Util.md5Hex(dto.getPassword());

            if (encode.equals(user.getPassword())) {
                token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
            }
        } catch (Exception e) {
            log.warn("username or password is incorrect {}", dto.getUsername());
        }
        return token;
    }

    @Override
    public User getUserByUserName(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(User::getUsername, username);
        return baseMapper.selectOne(wrapper);
    }
}