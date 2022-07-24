package com.lihd.sssp.service;

import com.lihd.sssp.entity.Department;
import com.lihd.sssp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/29 9:05
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAll(){
//        return departmentRepository.findAll();
        return departmentRepository.getAll();
    }
}
