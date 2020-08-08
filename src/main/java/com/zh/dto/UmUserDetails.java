package com.zh.dto;

import com.zh.entity.UmPermission;
import com.zh.entity.UmUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spring security中需要的用户详情
 */
public class UmUserDetails implements UserDetails {

    private static final long serialVersionUID = -4864060830846054675L;

    private UmUser umUser;

    private List<UmPermission> permissionList;

    public UmUserDetails(UmUser umUser, List<UmPermission> permissionList) {
        this.umUser = umUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(umPermission -> umPermission.getValue() != null)
                .map(umPermission -> new SimpleGrantedAuthority(umPermission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umUser.getStatus().equals(UmUser.ENABLED);
    }
}
