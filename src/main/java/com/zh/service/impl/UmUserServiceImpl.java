package com.zh.service.impl;

import com.zh.dao.UmUserDAO;
import com.zh.entity.UmPermission;
import com.zh.entity.UmUser;
import com.zh.service.UmUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmUserServiceImpl implements UmUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmUserServiceImpl.class);

    @Autowired
    private UmUserDAO umUserDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UmUser getUserByUsername(String username) {
        return umUserDAO.findByUsername(username);
    }

    @Override
    public UmUser register(UmUser umUser) {
        umUser.setCreateTime(new Date());
        umUser.setStatus(UmUser.ENABLED);
        UmUser byUsername = umUserDAO.findByUsername(umUser.getUsername());
        if (byUsername != null) {
            return null;
        }
        String encodePassword = passwordEncoder.encode(umUser.getPassword());
        umUser.setPassword(encodePassword);
        umUserDAO.insert(umUser);
        return umUser;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public List<UmPermission> getPermissionList(Integer userId) {
        return null;
    }
}
