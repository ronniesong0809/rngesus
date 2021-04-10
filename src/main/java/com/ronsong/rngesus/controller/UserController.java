package com.ronsong.rngesus.controller;

import com.ronsong.rngesus.common.api.ApiResult;
import com.ronsong.rngesus.model.dto.LoginDTO;
import com.ronsong.rngesus.model.dto.SignupDTO;
import com.ronsong.rngesus.model.entity.User;
import com.ronsong.rngesus.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.ronsong.rngesus.common.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private UserService UserService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ApiResult<Map<String, Object>> signup(@Valid @RequestBody SignupDTO dto) {
        User user = UserService.executeSignup((dto));
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("Signup failed!!");
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map, "Signup success!!");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = UserService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("Login failed!!");
        }

        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map, "Login success!!");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ApiResult<Object> logout() {
        return ApiResult.success("Logout success!!");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<User> getUser(@RequestHeader(value = USER_NAME) String username) {
        User user = UserService.getUserByUserName(username);
        return ApiResult.success(user);
    }
}
