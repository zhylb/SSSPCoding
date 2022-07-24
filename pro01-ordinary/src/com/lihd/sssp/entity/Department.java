package com.lihd.sssp.entity;

import org.junit.Test;

import javax.persistence.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/28 22:24
 */
@Cacheable
@Entity
@Table(name = "t_department")
public class Department {
    private Integer id;
    private String departmentName;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
