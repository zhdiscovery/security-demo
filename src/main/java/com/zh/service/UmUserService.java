package com.zh.service;

import com.zh.entity.UmPermission;
import com.zh.entity.UmUser;

import java.util.List;

public interface UmUserService {
    /**
     * 根据用户名获得用户
     */
    UmUser getUserByUsername(String username);

    /**
     * 注册
     */
    UmUser register(UmUser umUser);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 生成的jwt
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限
     */
    List<UmPermission> getPermissionList(Integer userId);

}
