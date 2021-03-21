package com.ronsong.rngesus.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ronsong.rngesus.common.exception.ApiAsserts;
import com.ronsong.rngesus.common.jwt.JwtUtil;
import com.ronsong.rngesus.mapper.UmsUserMapper;
import com.ronsong.rngesus.model.dto.LoginDTO;
import com.ronsong.rngesus.model.dto.SignupDTO;
import com.ronsong.rngesus.model.entity.UmsUser;
import com.ronsong.rngesus.service.IUmsUserService;
import com.ronsong.rngesus.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class IUmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Override
    public UmsUser executeSignup(SignupDTO dto) {
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(UmsUser::getUsername, dto.getUsername()).or().eq(UmsUser::getEmail, dto.getEmail());
        UmsUser umsUser = baseMapper.selectOne(wrapper);

        if (!ObjectUtil.isEmpty(umsUser)) {
            ApiAsserts.fail("username or email already exists");
        }

        UmsUser newUser = UmsUser.builder()
                .username(dto.getUsername())
                .alias(dto.getUsername())
                .email(dto.getEmail())
                .password(MD5Utils.getMD5(dto.getPassword()))
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
            LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();

            wrapper.eq(UmsUser::getUsername, dto.getUsername());
            UmsUser user = baseMapper.selectOne(wrapper);

            String encode = MD5Utils.getMD5(dto.getPassword());

            if (encode.equals(user.getPassword())) {
                token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
            }
        } catch (Exception e) {
            log.warn("username or password is incorrect {}", dto.getUsername());
        }
        return token;
    }

    @Override
    public UmsUser getUserByUserName(String username) {
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(UmsUser::getUsername, username);
        return baseMapper.selectOne(wrapper);
    }
}
