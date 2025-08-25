package com.GetHired.Controller;

import com.GetHired.Entities.Employee;
import com.GetHired.Service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @PostMapping("/add")
    public void add(@RequestBody Employee employee){
        employeeServices.addEmployee(employee);
    }

}
