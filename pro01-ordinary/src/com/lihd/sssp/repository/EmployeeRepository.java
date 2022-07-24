package com.lihd.sssp.repository;

import com.lihd.sssp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/28 23:27
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    Employee getEmployeeByLastName(String lastName);

}
