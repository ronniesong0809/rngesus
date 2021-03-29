package com.ronsong.rngesus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ronsong.rngesus.model.dto.LoginDTO;
import com.ronsong.rngesus.model.dto.SignupDTO;
import com.ronsong.rngesus.model.entity.User;

public interface UserService extends IService<User> {
    User executeSignup(SignupDTO dto);
    String executeLogin(LoginDTO dto);
    User getUserByUserName(String username);
}
