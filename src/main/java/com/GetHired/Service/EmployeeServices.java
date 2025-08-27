package com.GetHired.Service;

import com.GetHired.DAO.EmployeeDAO;
import com.GetHired.Entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServices {
    @Autowired
    private EmployeeDAO employeeDAO;

    public void addEmployee(Employee employee){
        employeeDAO.save(employee);
    }

    public Optional<Employee> findByEmail(String email) {
        return employeeDAO.findEmployeeByEmail(email);
    }


}
