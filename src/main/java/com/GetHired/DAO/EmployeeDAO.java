package com.GetHired.DAO;

import com.GetHired.Entities.Employee;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeDAO extends CrudRepository<Employee, Integer> {
    Iterable <Employee> findAll();

    Employee findById(int id);

    Optional<Employee> findEmployeeByEmail(String email);

}
