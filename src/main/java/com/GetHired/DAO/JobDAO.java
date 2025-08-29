package com.GetHired.DAO;

import com.GetHired.Entities.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDAO extends CrudRepository<Job, Long> {



}
