package com.GetHired.Service;

import com.GetHired.DAO.JobDAO;
import com.GetHired.Entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServices {

    @Autowired
    private JobDAO jobDAO;

    public Job postJob(Job job) {
        return jobDAO.save(job);
    }

    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        for (Job job : jobDAO.findAll()) {
            jobs.add(job);
        }
        return jobs;
    }
}
