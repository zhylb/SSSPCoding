package com.lihd.sssp.service;

import com.lihd.sssp.entity.Employee;
import com.lihd.sssp.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.PageRanges;
import java.awt.print.Pageable;
import java.util.Date;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/28 23:27
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<Employee> getPage(Integer pageNum,Integer pageSize){

        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize);

        Page<Employee> all = employeeRepository.findAll(pageRequest);

        return all;

    }

    @Transactional(readOnly = true)
    public Employee getOne(Integer id){
        Employee one = employeeRepository.findOne(id);
        return one;
    }


    @Transactional(readOnly = true)
    public Employee getByLastName(String lastName){
        return employeeRepository.getEmployeeByLastName(lastName);
    }

    @Transactional
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setCreateDate(new Date());
        }
        employeeRepository.saveAndFlush(employee);
    }

    @Transactional
    public Employee getEmployeeById(Integer id){
        return employeeRepository.findOne(id);
    }

    @Transactional
    public void deleteEmployeeById(Integer id){
        employeeRepository.delete(id);
    }


}
