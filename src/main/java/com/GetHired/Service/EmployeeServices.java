package com.GetHired.Service;

import com.GetHired.DAO.EmployeeDAO;
import com.GetHired.DAO.JobDAO;
import com.GetHired.Entities.Employee;
import com.GetHired.Entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private JobDAO jobDAO;

    public void addEmployee(Employee employee){
        employeeDAO.save(employee);
    }

    public Optional<Employee> findByEmail(String email) {
        return employeeDAO.findEmployeeByEmail(email);
    }




}
