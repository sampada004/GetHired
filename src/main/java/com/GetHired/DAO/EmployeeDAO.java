package com.GetHired.DAO;

import com.GetHired.Entities.Employee;
import com.GetHired.Entities.Recruiter;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDAO extends CrudRepository<Employee, Integer> {
    Iterable <Employee> findAll();

    Employee findById(int id);

}
