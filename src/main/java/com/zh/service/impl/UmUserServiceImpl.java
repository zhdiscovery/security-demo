package com.zh.service.impl;

import com.zh.dao.UmUserDAO;
import com.zh.entity.UmPermission;
import com.zh.entity.UmUser;
import com.zh.service.UmUserService;
import com.zh.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UmUserServiceImpl implements UmUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmUserServiceImpl.class);

    @Resource
    private UmUserDAO umUserDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

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
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("wrong password");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtUtil.generateToken(userDetails);
        } catch (UsernameNotFoundException e) {
            LOGGER.warn("登录异常：{}", e.getMessage());
        }
        return token;
    }

    @Override
    public List<UmPermission> getPermissionList(Integer userId) {
        return null;
    }
}
