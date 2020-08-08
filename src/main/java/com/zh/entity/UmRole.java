package com.zh.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体类
 */
public class UmRole implements Serializable {
    private static final long serialVersionUID = -7460127070490849060L;

    private Integer id;

    private String name;//名称

    private String description;//描述

    private Date createTime;//创建时间

    private Integer status;//启用状态：0->禁用，1->启用

    private Integer sort;//排序

    private Integer adminCount;//后台用户数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(Integer adminCount) {
        this.adminCount = adminCount;
    }

    @Override
    public String toString() {
        return "UmRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", sort=" + sort +
                ", adminCount=" + adminCount +
                '}';
    }
}
