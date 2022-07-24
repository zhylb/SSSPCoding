package com.lihd.sssp.controller;

import com.lihd.sssp.entity.Department;
import com.lihd.sssp.entity.Employee;
import com.lihd.sssp.service.DepartmentService;
import com.lihd.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/28 23:26
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    //提交 post
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    public String save(Employee employee){
        //那个post方法怎么找到我的 ？
        employeeService.save(employee);
        return "redirect:/emps";
    }


    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false) Integer id,Model model){

        if(id != null){
            Employee employee = employeeService.getEmployeeById(id);
            employee.setDepartment(null);
            model.addAttribute("employee",employee);
        }

    }

    //修改 put
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.PUT)
    public String modify(Employee employee){
        employeeService.save(employee);
        return "redirect:/emps";
    }


    //查询一个 回显 get
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String circumference(@PathVariable("id") Integer id,Model model){
        Employee one = employeeService.getOne(id);
        model.addAttribute("emp",one);
        model.addAttribute("depts",departmentService.getAll());
        return "emp/add";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/emps";
    }

    //查询所有  get
    @RequestMapping("/emps")
    public String listAll(@RequestParam(value = "pageNo",defaultValue = "1") String pageStr, Map<String ,Object> map){

        int pageNo = 1;
        try{
            pageNo = Integer.parseInt(pageStr);
            if(pageNo < 1) pageNo = 1;
        }catch (Exception e){}

        Page<Employee> page = employeeService.getPage(pageNo, 5);

        map.put("page",page);

        return "emp/empList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getOne(@RequestParam(value = "id",defaultValue = "1") Integer id,Model model){
        model.addAttribute("emp",new Employee());

        List<Department> all = departmentService.getAll();
        model.addAttribute("depts",all);

        return "emp/add";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxValidateLastName",method = RequestMethod.POST)
    public String ajaxValidateLastName(String lastName){
        Employee employee = employeeService.getByLastName(lastName);
        if (employee != null) {
            return "1";
        }else{
            return "0";
        }
    }

}
