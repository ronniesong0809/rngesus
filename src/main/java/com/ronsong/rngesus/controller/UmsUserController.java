package com.ronsong.rngesus.controller;

import com.ronsong.rngesus.common.api.ApiResult;
import com.ronsong.rngesus.model.dto.LoginDTO;
import com.ronsong.rngesus.model.dto.SignupDTO;
import com.ronsong.rngesus.model.entity.UmsUser;
import com.ronsong.rngesus.service.IUmsUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ums/user")
public class UmsUserController extends BaseController {
    @Resource
    private IUmsUserService iUmsUserService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ApiResult<Map<String, Object>> signup(@Valid @RequestBody SignupDTO dto) {
        UmsUser user = iUmsUserService.executeSignup((dto));
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("Signup failed!!");
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map, "Signup success!!");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = iUmsUserService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("Login failed!!");
        }

        Map<String, String> map = new HashMap<>(16);
        map.put("user", token);
        return ApiResult.success(map, "Login success!!");
    }
}
