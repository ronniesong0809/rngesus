package com.ronsong.rngesus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ronsong.rngesus.model.dto.LoginDTO;
import com.ronsong.rngesus.model.dto.SignupDTO;
import com.ronsong.rngesus.model.entity.UmsUser;

public interface IUmsUserService extends IService<UmsUser> {
    UmsUser executeSignup(SignupDTO dto);
    String executeLogin(LoginDTO dto);
    UmsUser getUserByUserName(String username);
}
