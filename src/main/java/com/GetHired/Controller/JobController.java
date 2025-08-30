package com.GetHired.Controller;

import com.GetHired.Entities.Job;
import com.GetHired.Service.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobServices jobServices;

    @PostMapping("/post")
    public void postJob(@RequestBody Job job){
        jobServices.postJob(job);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobServices.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }







}
