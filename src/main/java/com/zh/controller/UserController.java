package com.zh.controller;

import com.zh.common.CommonResult;
import com.zh.common.ResultCode;
import com.zh.dto.UmUserLoginParam;
import com.zh.entity.UmUser;
import com.zh.service.UmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UmUserService umUserService;

    @PostMapping("/login")
    public CommonResult<Map<String, Object>> login(@RequestBody UmUserLoginParam umUser) {
        if (StringUtils.isEmpty(umUser.getUsername()) || StringUtils.isEmpty(umUser.getPassword())) {
            return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), "empty username or password");
        }
        String token = umUserService.login(umUser.getUsername(), umUser.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("invalid username or password");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        return CommonResult.success(result);
    }

    @PostMapping("/register")
    public CommonResult register(@RequestBody UmUser umUser) {
        UmUser register = umUserService.register(umUser);
        if (register == null) {
            return CommonResult.failed("username already exists");
        }
        return CommonResult.success(register);
    }

}
