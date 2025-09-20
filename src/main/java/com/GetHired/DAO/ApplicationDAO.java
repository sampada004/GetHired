package com.GetHired.DAO;

import com.GetHired.Entities.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationDAO extends CrudRepository<Application, Long> {

    List<Application> findByJobId(Long jobId);


    List<Application> findByEmployeeId(Long employeeId);
}