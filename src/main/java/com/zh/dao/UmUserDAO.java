package com.zh.dao;

import com.zh.entity.UmUser;
import org.apache.ibatis.annotations.Param;

public interface UmUserDAO {

    /**
     * 根据用户名查找用户信息
     */
    UmUser findByUsername(String username);

    /**
     * 添加用户信息
     */
    int insert(@Param("umUser") UmUser umUser);

}
