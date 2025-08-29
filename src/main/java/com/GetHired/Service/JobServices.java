package com.GetHired.Service;

import com.GetHired.DAO.JobDAO;
import com.GetHired.Entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServices {

    @Autowired
    private JobDAO jobDAO;

    public Job postJob(Job job) {
        return jobDAO.save(job);
    }
}
