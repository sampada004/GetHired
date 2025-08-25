package com.GetHired.Service;

import com.GetHired.DAO.EmployeeDAO;
import com.GetHired.Entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices {
    @Autowired
    private EmployeeDAO employeeDAO;

    public void addEmployee(Employee employee){
        employeeDAO.save(employee);
    }
}
