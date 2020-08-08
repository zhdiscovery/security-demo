package com.zh;

import com.zh.dao.UmUserDAO;
import com.zh.entity.UmUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootTest
class SecurityDemoApplicationTests {

    @Autowired
    private UmUserDAO umUserDAO;

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123"));
    }

    @Test
    void testUmUserDAO() {
        UmUser umUser = new UmUser();
        umUser.setUsername("zh");
        umUser.setCreateTime(new Date());
        umUser.setEmail("qq");
        umUser.setIcon("touxiang");
        umUser.setLoginTime(new Date());
        umUser.setNickName("zh");
        umUser.setNote("note");
        umUser.setPassword("123");
        umUser.setStatus(UmUser.ENABLED);
        umUserDAO.insert(umUser);
        System.out.println(umUser);
    }

    @Test
    void testSelect() {
        UmUser zh = umUserDAO.findByUsername("zh");
        System.out.println(zh);
    }

}
