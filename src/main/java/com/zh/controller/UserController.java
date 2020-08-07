package com.zh.controller;

import com.zh.common.CommonResult;
import com.zh.common.ResultCode;
import com.zh.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public CommonResult<Map<String, Object>> login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), "username or password error");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), "password error");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtUtil.generateToken(userDetails);
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        return CommonResult.success(result);
    }

    @PostMapping("/register")
    public CommonResult register(@RequestBody String username, @RequestBody String password) {
        InMemoryUserDetailsManager manager = (InMemoryUserDetailsManager) userDetailsService;
        String encode = passwordEncoder.encode(password);
        manager.createUser(User.withUsername(username).password(encode).authorities("r2").build());
        return CommonResult.success("ok");
    }

}
